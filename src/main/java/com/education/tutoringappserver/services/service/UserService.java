package com.education.tutoringappserver.services.service;

import com.education.tutoringappserver.payloads.requests.LoginRequest;
import com.education.tutoringappserver.payloads.requests.SignupRequest;
import com.education.tutoringappserver.payloads.responses.LoginResponse;

public interface UserService {
    /**
     * @param request {@link LoginRequest}
     * @return {@link LoginResponse}
     */
    LoginResponse authenticateToken(LoginRequest request);

    /**
     * @param request {@link SignupRequest}
     * @return {@link LoginResponse}
     */
    LoginResponse signUp(SignupRequest request);
}
