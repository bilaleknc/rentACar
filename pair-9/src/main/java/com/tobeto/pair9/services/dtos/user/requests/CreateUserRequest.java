package com.tobeto.pair9.services.dtos.user.requests;

import com.tobeto.pair9.entities.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    private String email;
    private String password;
    private String username;
    private List<Role> roles;

}
