package com.tobeto.pair9.controllers;

import com.tobeto.pair9.services.abstracts.AuthService;
import com.tobeto.pair9.services.dtos.user.requests.CreateUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    private void register(@RequestBody CreateUserRequest request){
        authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
