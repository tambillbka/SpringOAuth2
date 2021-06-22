package com.cuongnn.tutoringappserver.services.service;

import com.cuongnn.tutoringappserver.payloads.requests.SignupRequest;
import com.cuongnn.tutoringappserver.payloads.responses.LoginResponse;

public interface UserService {
    /**
     * @param username String
     * @param password String
     * @return {@link LoginResponse}
     */
    LoginResponse login(String username, String password);

    /**
     * @param request {@link SignupRequest}
     * @return {@link LoginResponse}
     */
    LoginResponse signUp(SignupRequest request);
}
