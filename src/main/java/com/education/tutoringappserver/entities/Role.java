package com.education.tutoringappserver.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    /**
     * ROLE_ADMIN - System Admin,
     * ROLE_USER - For Student, user after login
     * ROLE_TUTOR - For tutor
     */
    ROLE_ADMIN, ROLE_USER, ROLE_TUTOR;

    /**
     * @return Role.getName() to Authentication
     */
    @Override
    public String getAuthority() {
        return name();
    }
}
