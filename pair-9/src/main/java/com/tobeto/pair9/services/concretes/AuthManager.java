package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.services.JwtService;
import com.tobeto.pair9.entities.concretes.RefreshToken;
import com.tobeto.pair9.entities.concretes.User;
import com.tobeto.pair9.repositories.RefreshTokenRepository;
import com.tobeto.pair9.repositories.UserRepository;
import com.tobeto.pair9.services.abstracts.AuthService;
import com.tobeto.pair9.services.dtos.auth.responses.TokenResponse;
import com.tobeto.pair9.services.dtos.user.requests.CreateUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository tokenRepository;

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
            saveOrUpdateRefreshToken(refreshToken, user);
            return new TokenResponse(accessToken);
        }
        return null;
    }

    @Override
    @Transactional
    public String refreshToken(String userName) {

        User user = userRepository.findByUsername(userName).orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı"));
        Optional<RefreshToken> existingToken = tokenRepository.findByUser(user);
        if (existingToken.isEmpty()){
            throw new RuntimeException("Token bulunamadı");
        }
        RefreshToken refreshToken = existingToken.get();
        if (refreshToken.getExpiryDate().before(new Date())){
            tokenRepository.deleteByUser(user);
            throw new RuntimeException("Token süresi dolmuş");
        }
        if (!jwtService.validateToken(refreshToken.getToken(), userName)){
            throw new RuntimeException("Token geçerli değil");
        }
        return jwtService.generateToken(user);
    }

    @Transactional
    public void logout(String userName){
        User user = userRepository.findByUsername(userName).orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı"));
        tokenRepository.deleteByUser(user);
    }

    public void saveOrUpdateRefreshToken(String token, User user) {
        // RefreshToken kontrolü
        Optional<RefreshToken> existingToken = tokenRepository.findByUser(user);
        RefreshToken refreshToken;
        if (existingToken.isPresent()) {
            // Mevcut token varsa güncelle
            refreshToken = existingToken.get();
            System.out.println("Token already saved for user "+ user.getId() + ", updating...");
        } else {
            System.out.println("Token not found for user "+ user.getId() + ", creating new...");
            // Yoksa yeni bir token oluştur
            refreshToken = new RefreshToken();
            refreshToken.setUser(user);
        }
        refreshToken.setToken(token);
        refreshToken.setExpiryDate(jwtService.extractExpiration(token));
        tokenRepository.save(refreshToken);
    }

    @Override
    public boolean existsId(int id) {
        return userRepository.existsById(id);
    }

    private boolean existEmail(String email){
        return userRepository.existsUserByEmail(email);
    }
}
