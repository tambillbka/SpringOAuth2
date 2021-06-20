package com.cuongnn.tutoringappserver.services.serviceImpl;

import com.cuongnn.tutoringappserver.common.exception.ServiceStatus;
import com.cuongnn.tutoringappserver.common.utils.Strings;
import com.cuongnn.tutoringappserver.payloads.responses.LoginResponse;
import com.cuongnn.tutoringappserver.repositories.repository.UserRepository;
import com.cuongnn.tutoringappserver.services.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse login(String username, String password) {
        // Refactoring input to authenticate
        username = Strings.refactor(username);;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return null;
        } catch (AuthenticationException e) {
            throw new ServiceStatus("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
