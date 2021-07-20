package com.education.tutoringappserver.common.config;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "app")
@NoArgsConstructor
@AllArgsConstructor
public class AppProperties {
    private final Auth auth = new Auth();
    private final OAuth2 oAuth2 = new OAuth2();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;
    }

    @Data
    @NoArgsConstructor
    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = Lists.newArrayList();
    }
}
