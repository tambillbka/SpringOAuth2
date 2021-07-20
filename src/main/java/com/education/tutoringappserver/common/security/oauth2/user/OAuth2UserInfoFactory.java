package com.education.tutoringappserver.common.security.oauth2.user;

import com.education.tutoringappserver.common.exception.OAuth2AuthenticationProcessingException;
import com.education.tutoringappserver.common.security.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.google.name())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.name())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Login with ".concat(registrationId).concat(" is not supported yet."));
        }
    }

}
