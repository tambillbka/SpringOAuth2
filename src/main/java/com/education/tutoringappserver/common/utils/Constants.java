package com.education.tutoringappserver.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    //A
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_SCOPE_DESCRIPTION = "accessEverything";
    public static final String API_INFO_TITLE = "JSON Web Token Authentication API";
    public static final String AUTH = "auth";
    public static final String ALL = "*";
    public static final String ALL_PATTERN = "/**";

    //B
    public static final String BEARER = "Bearer ";

    //C
    public static final int COOKIE_EXPIRE_SECONDS = 180;

    //D
    public static final String DATA = "data";
    public static final String DELETE = "DELETE";

    //E
    public static long EXPIRE_LENGTH = 86400000;
    public static final String ERROR_PATH = "/error";
    public static final String ENTRY_POINT_LOG_ERR = "Responding with unauthorized error, Message - {}";
    public static final String EMAIL = "email";
    public static final String ERROR = "error";

    //F

    //G
    public static final String GLOBAL = "global";
    public static final String GET = "GET";

    //H
    public static final String HEADER = "header";
    public static final String HEAD = "HEAD";

    //I
    public static final String INVALID_TOKEN = "Expired or invalid JWT token";
    public static final String INVALID_USERNAME_PASSWORD = "Invalid username/password supplied!";
    public static final String ID = "id";

    //J

    //K

    //L

    //M
    public static final String META_DATA_DESCRIPTION = "Use the `admin-admin` or `client-client` users to test the authorization filters. "
            .concat("Once you have successfully logged in and obtained the token, ")
            .concat("you should click on the right top button `Authorize` and introduce it with the prefix \"Bearer \".");
    public static final String META_DATA_VERSION = "1.0.0";
    public static final String MAIL_AUTHOR = "phamngoctam.bka@gmail.com";
    public static final long MAX_AGE_SECS = 3600;

    //N
    public static final String NAME = "name";

    //O
    public static final String OPTIONS = "OPTIONS";
    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";

    //P
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String PATCH = "PATCH";
    public static final String PATH = "/";
    public static final String PICTURE = "picture";

    //Q

    //R
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    public static final String REDIRECT_FAIL = "OAuth2 redirect failure!";

    //S
    public static String SECRET_KEY = "j12W!apGjHKLtO89*";
    public static final String SUB = "sub";

    //T
    public static final String TOKEN = "token";

    //U
    public static final String USER_TAG = "users";
    public static final String USER_TAG_DESCRIPTION = "Operations about users";
    public static final String USERNAME_OR_EMAIL_EXIST = "Username or Email is exist!";
    public static final String URL = "url";

    //V

    //W

    //X

    //Y

    //Z
}
