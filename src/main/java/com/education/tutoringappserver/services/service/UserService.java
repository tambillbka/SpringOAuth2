package com.education.tutoringappserver.services.service;

import com.education.tutoringappserver.payloads.requests.LoginRequest;
import com.education.tutoringappserver.payloads.requests.SignupRequest;
import com.education.tutoringappserver.payloads.responses.TokenResponse;

public interface UserService {
    /**
     * @param request {@link LoginRequest}
     * @return {@link TokenResponse}
     */
    TokenResponse authenticateToken(LoginRequest request);

    /**
     * @param request {@link SignupRequest}
     * @return {@link TokenResponse}
     */
    TokenResponse signUp(SignupRequest request);
}
