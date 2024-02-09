package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.services.JwtService;
import com.tobeto.pair9.entities.concretes.User;
import com.tobeto.pair9.repositories.UserRepository;
import com.tobeto.pair9.services.abstracts.AuthService;
import com.tobeto.pair9.services.dtos.auth.responses.TokenResponse;
import com.tobeto.pair9.services.dtos.user.requests.CreateUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.LoginRequest;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(CreateUserRequest createUserRequest) {
        if(existEmail(createUserRequest.getEmail())){
            throw new RuntimeException("User already saved");
        }
        User user = User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .role("USER")
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .build();
        userRepository.save(user);
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()){
            var user = userRepository.findByUsername(loginRequest.getUserName()).orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı"));
            String accessToken = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            return new TokenResponse(accessToken, refreshToken);
        }
        return null;
    }

    @Override
    public String refreshToken(String refreshToken) {
        String userName = jwtService.extractUser(refreshToken);
        String token = jwtService.extractToken(refreshToken);
        if (!jwtService.validateToken(token, userName)){
            throw new RuntimeException("Token geçerli değil");
        }
        System.out.println("userName !!!!!!!!!!!!!!!!!!!!" + userName);
        User user = userRepository.findByUsername(userName).orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı"));
        return jwtService.generateToken(user);
    }

    @Override
    public boolean existsId(int id) {
        return userRepository.existsById(id);
    }

    private boolean existEmail(String email){
        return userRepository.existsUserByEmail(email);
    }
}
