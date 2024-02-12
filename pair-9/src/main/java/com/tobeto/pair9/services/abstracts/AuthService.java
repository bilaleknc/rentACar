package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.services.dtos.authenticaton.requests.AuthenticationRequest;
import com.tobeto.pair9.services.dtos.authenticaton.requests.RegisterRequest;
import com.tobeto.pair9.services.dtos.authenticaton.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request,
                      HttpServletResponse response) throws IOException;

}
