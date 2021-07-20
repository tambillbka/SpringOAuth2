package com.education.tutoringappserver.common.security.oauth2;

import com.education.tutoringappserver.common.exception.OAuth2AuthenticationProcessingException;
import com.education.tutoringappserver.common.security.AuthProvider;
import com.education.tutoringappserver.common.security.UserPrincipal;
import com.education.tutoringappserver.common.security.oauth2.user.OAuth2UserInfo;
import com.education.tutoringappserver.common.security.oauth2.user.OAuth2UserInfoFactory;
import com.education.tutoringappserver.entities.Role;
import com.education.tutoringappserver.entities.User;
import com.education.tutoringappserver.repositories.repository.UserRepository;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.education.tutoringappserver.common.utils.Constants.INVALID_USERNAME_PASSWORD;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return this.processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest request, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                request.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes()
        );

        if (Strings.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException(INVALID_USERNAME_PASSWORD);
        }

        User user = userRepository.findByEmail(oAuth2UserInfo.getEmail());

        if (Objects.nonNull(user)) {
            if (user.getProvider().equals(AuthProvider.valueOf(request.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Sign up with "
                        .concat(user.getProvider().toString())
                        .concat(" account. Account ")
                        .concat(user.getEmail())
                        .concat(" exists"));
            }
            user = this.updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = this.registerNewUser(request, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getName());
        user.setUsername(oAuth2UserInfo.getEmail());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        user.setRoles(Sets.newHashSet(Role.ROLE_USER));
        user.setName(oAuth2UserInfo.getName());
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        existingUser.setName(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }
}
