package com.education.tutoringappserver.common.security.oauth2.user;

import java.util.Map;

import static com.education.tutoringappserver.common.utils.Constants.EMAIL;
import static com.education.tutoringappserver.common.utils.Constants.NAME;
import static com.education.tutoringappserver.common.utils.Constants.PICTURE;
import static com.education.tutoringappserver.common.utils.Constants.SUB;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {
    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get(SUB);
    }

    @Override
    public String getName() {
        return (String) attributes.get(NAME);
    }

    @Override
    public String getEmail() {
        return (String) attributes.get(EMAIL);
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get(PICTURE);
    }
}
