package com.education.tutoringappserver;

import com.education.tutoringappserver.common.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableConfigurationProperties(AppProperties.class)
public class TutoringAppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutoringAppServerApplication.class, args);
    }

}
