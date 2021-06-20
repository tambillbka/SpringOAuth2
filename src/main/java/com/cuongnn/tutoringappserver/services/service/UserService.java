package com.cuongnn.tutoringappserver.services.service;

import com.cuongnn.tutoringappserver.payloads.responses.LoginResponse;

public interface UserService {
    /**
     * @param username String
     * @param password String
     * @return {@link LoginResponse}
     */
    LoginResponse login(String username, String password);
}
