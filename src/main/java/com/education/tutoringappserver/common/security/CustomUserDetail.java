package com.education.tutoringappserver.common.security;

import com.education.tutoringappserver.common.utils.Strings;
import com.education.tutoringappserver.entities.User;
import com.education.tutoringappserver.repositories.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetail implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetail(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(Strings.refactor(username));

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Cannot find user: ".concat(Strings.refactor(username)));
        }

        return UserPrincipal.create(user);
    }
}
