package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.services.JwtService;
import com.tobeto.pair9.entities.concretes.User;
import com.tobeto.pair9.repositories.UserRepository;
import com.tobeto.pair9.services.abstracts.AuthService;
import com.tobeto.pair9.services.dtos.user.requests.CreateUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.LoginRequest;
import lombok.AllArgsConstructor;
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
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(loginRequest.getUserName());
        }
        throw new RuntimeException("Kullanıcı adı ya da şifre yanlış");
    }

    @Override
    public boolean existsId(int id) {
        return userRepository.existsById(id);
    }

    private boolean existEmail(String email){
        return userRepository.existsUserByEmail(email);
    }
}
