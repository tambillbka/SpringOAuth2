package com.education.tutoringappserver.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.education.tutoringappserver.common.utils.Constants.API_INFO_TITLE;
import static com.education.tutoringappserver.common.utils.Constants.AUTHORIZATION;
import static com.education.tutoringappserver.common.utils.Constants.AUTHORIZATION_SCOPE_DESCRIPTION;
import static com.education.tutoringappserver.common.utils.Constants.ERROR_PATH;
import static com.education.tutoringappserver.common.utils.Constants.GLOBAL;
import static com.education.tutoringappserver.common.utils.Constants.HEADER;
import static com.education.tutoringappserver.common.utils.Constants.MAIL_AUTHOR;
import static com.education.tutoringappserver.common.utils.Constants.META_DATA_DESCRIPTION;
import static com.education.tutoringappserver.common.utils.Constants.META_DATA_VERSION;
import static com.education.tutoringappserver.common.utils.Constants.USER_TAG;
import static com.education.tutoringappserver.common.utils.Constants.USER_TAG_DESCRIPTION;
import static com.google.common.base.Predicates.not;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(not(PathSelectors.regex(ERROR_PATH)))
                .build()
                .apiInfo(this.metadata())
                .useDefaultResponseMessages(false)
                .securitySchemes(Collections.singletonList(this.apiKey()))
                .securityContexts(Collections.singletonList(this.securityContext()))
                .tags(new Tag(USER_TAG, USER_TAG_DESCRIPTION))
                .genericModelSubstitutes(Optional.class);
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title(API_INFO_TITLE)
                .description(META_DATA_DESCRIPTION)
                .version(META_DATA_VERSION)
                .contact(new Contact(null, null, MAIL_AUTHOR))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION, AUTHORIZATION, HEADER);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(this.defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope(GLOBAL, AUTHORIZATION_SCOPE_DESCRIPTION);
        return Collections.singletonList(new SecurityReference(AUTHORIZATION, authorizationScopes));
    }
}
