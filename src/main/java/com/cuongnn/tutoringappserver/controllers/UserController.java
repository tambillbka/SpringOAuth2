package com.cuongnn.tutoringappserver.controllers;

import com.cuongnn.tutoringappserver.payloads.requests.LoginRequest;
import com.cuongnn.tutoringappserver.payloads.responses.LoginResponse;
import com.cuongnn.tutoringappserver.services.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/authentication/login")
    @ApiOperation(value = "Login system api")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(userService.login(request.getUsername(), request.getPassword()), HttpStatus.OK);
    }
}
