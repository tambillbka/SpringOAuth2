package com.education.tutoringappserver.services.service;

import com.education.tutoringappserver.payloads.requests.SignupRequest;
import com.education.tutoringappserver.payloads.responses.LoginResponse;

public interface UserService {
    /**
     * @param username String
     * @param password String
     * @return {@link LoginResponse}
     */
    LoginResponse authenticateToken(String username, String password);

    /**
     * @param request {@link SignupRequest}
     * @return {@link LoginResponse}
     */
    LoginResponse signUp(SignupRequest request);
}
