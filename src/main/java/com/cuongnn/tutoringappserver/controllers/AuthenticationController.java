package com.cuongnn.tutoringappserver.controllers;

import com.cuongnn.tutoringappserver.payloads.requests.LoginRequest;
import com.cuongnn.tutoringappserver.payloads.requests.SignupRequest;
import com.cuongnn.tutoringappserver.payloads.responses.LoginResponse;
import com.cuongnn.tutoringappserver.services.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/authentication/login")
    @ApiOperation(value = "Login system api")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong!"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied!")
    })
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(userService.login(request.getUsername(), request.getPassword()), HttpStatus.OK);
    }

    @PostMapping(value = "/authentication/signup")
    @ApiOperation(value = "Signup - Create new user (No authentication)")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Username is already in use")})
    public ResponseEntity<LoginResponse> signUp(
            @ApiParam(value = "Signup Body data") @RequestBody SignupRequest request
    ) {
        return new ResponseEntity<>(userService.signUp(request), HttpStatus.OK);
    }
}
