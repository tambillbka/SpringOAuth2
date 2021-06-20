package com.cuongnn.tutoringappserver.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Document(collection = "users")
public class User extends BaseEntity {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private Set<Role> role;
}
