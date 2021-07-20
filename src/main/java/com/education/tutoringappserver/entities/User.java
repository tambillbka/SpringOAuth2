package com.education.tutoringappserver.entities;

import com.education.tutoringappserver.common.security.AuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Document(collection = "users")
public class User extends BaseEntity {
    @Id
    private String id;

    private String username;

    @Email
    private String email;

    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    private Set<Role> roles;

    private String imageUrl;

    @NotNull
    private AuthProvider provider;

    private String providerId;

    private String name;
}
