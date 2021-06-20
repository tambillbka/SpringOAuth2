package com.cuongnn.tutoringappserver.repositories.custom;

import com.cuongnn.tutoringappserver.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository {
    /**
     * Find user by username
     * @param username String
     * @return {@link User}
     */
    User findByUsername(String username);
}
