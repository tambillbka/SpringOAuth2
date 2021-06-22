package com.cuongnn.tutoringappserver.payloads.requests;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class LoginRequest {
    @ApiParam(value = "username")
    private String username;
    @ApiParam(value = "password")
    private String password;
}
