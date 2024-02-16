package com.tobeto.pair9.services.dtos.authenticaton.requests;

import com.tobeto.pair9.entities.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String email;

    private String username;

    private String password;

    private Role role;

}
