package com.education.tutoringappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class TutoringAppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutoringAppServerApplication.class, args);
    }

}
