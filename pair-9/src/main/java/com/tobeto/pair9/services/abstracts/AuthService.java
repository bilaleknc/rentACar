package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.services.dtos.auth.responses.TokenResponse;
import com.tobeto.pair9.services.dtos.user.requests.CreateUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.LoginRequest;

public interface AuthService {
    void register(CreateUserRequest createUserRequest);

    TokenResponse login(LoginRequest loginRequest);

    String refreshToken(String refreshToken);

    boolean existsId(int id);
}
