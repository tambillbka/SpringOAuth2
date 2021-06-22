package com.education.tutoringappserver.services.serviceImpl;

import com.education.tutoringappserver.common.exception.ServiceStatus;
import com.education.tutoringappserver.common.security.JwtTokenProvider;
import com.education.tutoringappserver.common.utils.Strings;
import com.education.tutoringappserver.entities.User;
import com.education.tutoringappserver.payloads.requests.SignupRequest;
import com.education.tutoringappserver.payloads.responses.LoginResponse;
import com.education.tutoringappserver.repositories.repository.UserRepository;
import com.education.tutoringappserver.services.service.UserService;
import com.education.tutoringappserver.common.utils.Constants;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new ServiceStatus(Constants.INVALID_USERNAME_PASSWORD, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public LoginResponse signUp(SignupRequest request) {
        if (userRepository.existByUsernameOrEmail(Strings.refactor(request.getUsername()))) {
            throw new ServiceStatus(Constants.USERNAME_OR_EMAIL_EXIST, HttpStatus.UNPROCESSABLE_ENTITY);
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
