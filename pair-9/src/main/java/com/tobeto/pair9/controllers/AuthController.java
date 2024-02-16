package com.tobeto.pair9.controllers;

import com.tobeto.pair9.services.abstracts.AuthService;
import com.tobeto.pair9.services.dtos.auth.responses.TokenResponse;
import com.tobeto.pair9.services.dtos.user.requests.CreateUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    private void register(@RequestBody CreateUserRequest request){
        authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestParam("userName") String userName) {
        return ResponseEntity.ok(authService.refreshToken(userName));
    }

    // Logout işlemi için endpoint
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("userName") String userName) {
        authService.logout(userName);
        return ResponseEntity.ok().body("Logout successful");
    }


}
