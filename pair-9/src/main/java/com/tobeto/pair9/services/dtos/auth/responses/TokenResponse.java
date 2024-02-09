package com.tobeto.pair9.services.dtos.auth.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;

}
