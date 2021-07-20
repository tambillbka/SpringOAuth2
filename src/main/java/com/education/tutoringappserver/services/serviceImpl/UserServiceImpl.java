package com.education.tutoringappserver.services.serviceImpl;

import com.education.tutoringappserver.common.exception.ServiceStatus;
import com.education.tutoringappserver.common.security.AuthProvider;
import com.education.tutoringappserver.common.security.JwtTokenProvider;
import com.education.tutoringappserver.common.utils.Constants;
import com.education.tutoringappserver.common.utils.Strings;
import com.education.tutoringappserver.entities.User;
import com.education.tutoringappserver.payloads.requests.LoginRequest;
import com.education.tutoringappserver.payloads.requests.SignupRequest;
import com.education.tutoringappserver.payloads.responses.TokenResponse;
import com.education.tutoringappserver.repositories.repository.UserRepository;
import com.education.tutoringappserver.services.service.UserService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponse authenticateToken(LoginRequest request) {
        String username = Strings.refactor(request.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            request.getPassword())
            );

            return new TokenResponse(jwtTokenProvider.createToken(username,
                    Lists.newArrayList(userRepository.findByUsername(username).getRoles())));
        } catch (AuthenticationException e) {
            throw new ServiceStatus(Constants.INVALID_USERNAME_PASSWORD, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public TokenResponse signUp(SignupRequest request) {
        if (userRepository.existByUsernameOrEmail(Strings.refactor(request.getUsername()))) {
            throw new ServiceStatus(Constants.USERNAME_OR_EMAIL_EXIST, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        user.setProvider(AuthProvider.local);

        TokenResponse response = new TokenResponse(jwtTokenProvider.createToken(request.getUsername(),
                Lists.newArrayList(request.getRoles())));

        userRepository.save(user);
        return response;
    }
}
