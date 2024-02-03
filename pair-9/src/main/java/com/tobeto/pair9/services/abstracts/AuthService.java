package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.services.dtos.user.requests.CreateUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.LoginRequest;

public interface AuthService {
    void register(CreateUserRequest createUserRequest);

    String login(LoginRequest loginRequest);

    boolean existsId(int id);
}
