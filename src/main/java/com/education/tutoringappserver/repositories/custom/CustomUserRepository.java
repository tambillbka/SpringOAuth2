package com.education.tutoringappserver.repositories.custom;

import com.education.tutoringappserver.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository {
    /**
     * Find user by username
     * @param username String
     * @return {@link User}
     */
    User findByUsername(String username);

    /**
     * Check exist user in database
     * @param username String
     * @return boolean - checked value
     */
    boolean existByUsernameOrEmail(String username);
}
