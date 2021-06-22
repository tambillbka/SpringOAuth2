package com.education.tutoringappserver.payloads.requests;

import com.education.tutoringappserver.entities.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @ApiModelProperty(value = "Username", position = 1)
    private String username;

    @ApiModelProperty(value = "Password", position = 2)
    private String password;

    @ApiModelProperty(value = "Role", position = 3)
    private Set<Role> roles;
}
