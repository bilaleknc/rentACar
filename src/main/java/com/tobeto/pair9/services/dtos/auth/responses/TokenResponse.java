package com.tobeto.pair9.services.dtos.auth.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TokenResponse {

    private String token;
}
