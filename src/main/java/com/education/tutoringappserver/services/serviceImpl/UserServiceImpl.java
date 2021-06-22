package com.cuongnn.tutoringappserver.services.serviceImpl;

import com.cuongnn.tutoringappserver.common.exception.ServiceStatus;
import com.cuongnn.tutoringappserver.common.security.JwtTokenProvider;
import com.cuongnn.tutoringappserver.common.utils.Strings;
import com.cuongnn.tutoringappserver.entities.User;
import com.cuongnn.tutoringappserver.payloads.requests.SignupRequest;
import com.cuongnn.tutoringappserver.payloads.responses.LoginResponse;
import com.cuongnn.tutoringappserver.repositories.repository.UserRepository;
import com.cuongnn.tutoringappserver.services.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.cuongnn.tutoringappserver.common.utils.Constants.INVALID_USERNAME_PASSWORD;
import static com.cuongnn.tutoringappserver.common.utils.Constants.USERNAME_OR_EMAIL_EXIST;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public LoginResponse authenticateToken(String username, String password) {
        username = Strings.refactor(username);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return new LoginResponse(jwtTokenProvider.createToken(username,
                    Lists.newArrayList(userRepository.findByUsername(username).getRoles())));
        } catch (AuthenticationException e) {
            throw new ServiceStatus(INVALID_USERNAME_PASSWORD, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public LoginResponse signUp(SignupRequest request) {
        if (userRepository.existByUsernameOrEmail(Strings.refactor(request.getUsername()))) {
            throw new ServiceStatus(USERNAME_OR_EMAIL_EXIST, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        userRepository.save(user);

        return new LoginResponse(
                jwtTokenProvider.createToken(request.getUsername(),
                        Lists.newArrayList(request.getRoles()))
        );
    }
}
