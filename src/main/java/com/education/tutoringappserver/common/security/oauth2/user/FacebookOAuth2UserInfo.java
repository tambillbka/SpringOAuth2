package com.education.tutoringappserver.common.security.oauth2.user;

import java.util.Map;

import static com.education.tutoringappserver.common.utils.Constants.DATA;
import static com.education.tutoringappserver.common.utils.Constants.EMAIL;
import static com.education.tutoringappserver.common.utils.Constants.ID;
import static com.education.tutoringappserver.common.utils.Constants.NAME;
import static com.education.tutoringappserver.common.utils.Constants.PICTURE;
import static com.education.tutoringappserver.common.utils.Constants.URL;

public class FacebookOAuth2UserInfo extends OAuth2UserInfo {

    public FacebookOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get(ID);
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
        if (attributes.containsKey(PICTURE)) {
            Map<String, Object> pictureMap = (Map<String, Object>) attributes.get(PICTURE);
            if (pictureMap.containsKey(DATA)) {
                Map<String, Object> dataMap = (Map<String, Object>) pictureMap.get(DATA);
                if (dataMap.containsKey(URL)) {
                    return (String) dataMap.get(URL);
                }
            }
        }
        return null;
    }
}
