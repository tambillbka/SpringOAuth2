package com.cuongnn.tutoringappserver.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    //A
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_SCOPE_DESCRIPTION = "accessEverything";
    public static final String API_INFO_TITLE = "JSON Web Token Authentication API";
    public static final String AUTH = "auth";

    //B
    public static final String BEARER = "Bearer ";

    //E
    public static long EXPIRE_LENGTH = 86400000;
    public static final String ERROR_PATH = "/error";

    //G
    public static final String GLOBAL = "global";

    //H
    public static final String HEADER = "header";

    //I
    public static final String INVALID_TOKEN = "Expired or invalid JWT token";
    public static final String INVALID_USERNAME_PASSWORD = "Invalid username/password supplied!";

    //M
    public static final String META_DATA_DESCRIPTION = "Use the `admin-admin` or `client-client` users to test the authorization filters. "
            .concat("Once you have successfully logged in and obtained the token, ")
            .concat("you should click on the right top button `Authorize` and introduce it with the prefix \"Bearer \".");
    public static final String META_DATA_VERSION = "1.0.0";
    public static final String MAIL_AUTHOR = "phamngoctam.bka@gmail.com";

    //U
    public static final String USER_TAG = "users";
    public static final String USER_TAG_DESCRIPTION = "Operations about users";
    public static final String USERNAME_OR_EMAIL_EXIST = "Username or Email is exist!";

    //S
    public static String SECRET_KEY = "j12W!apGjHKLtO89*";
}
