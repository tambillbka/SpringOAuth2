package com.education.tutoringappserver.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.education.tutoringappserver.common.utils.Constants.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(ALL_PATTERN)
                .allowedOrigins(ALL)
                .allowedMethods(GET, PUT, POST, PATCH, DELETE, OPTIONS, HEAD)
                .allowedHeaders(ALL)
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }
}
