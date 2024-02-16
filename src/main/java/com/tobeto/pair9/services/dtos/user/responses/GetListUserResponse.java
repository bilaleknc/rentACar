package com.tobeto.pair9.services.dtos.user.responses;

import com.tobeto.pair9.entities.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListUserResponse {

    private Integer id;

    private String email;

    private String username;

    private Role role;
}
