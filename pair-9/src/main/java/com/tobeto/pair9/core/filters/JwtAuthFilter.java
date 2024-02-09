package com.tobeto.pair9.core.filters;

import com.tobeto.pair9.core.services.JwtService;
import com.tobeto.pair9.entities.concretes.User;
import com.tobeto.pair9.repositories.UserRepository;
import com.tobeto.pair9.services.abstracts.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository userRepository;




    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwtHeader = request.getHeader("Authorization");
        if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
            String jwt = jwtHeader.substring(7);
            String username = jwtService.extractUser(jwt);
            var user2 = userRepository.findByUsername(username).orElse(null);
            if (username != null) {
                UserDetails user = userService.loadUserByUsername(username);
                if (jwtService.validateToken(jwt, user))                                     //Herşey başarılı, Giriş sağlanabilir.
                    createAuthenticationToken(request, user, username, user2);
                else                                                                        // eğer access token geçerli değilse refresh token kontrol edilecek
                    checkRefreshToken(request, response, filterChain, user, username, user2);
            }
        }
        filterChain.doFilter(request, response);
    }




    private void checkRefreshToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, UserDetails user, String username, User user2) {
        String refreshTokenHeader = request.getHeader("Refresh-Token");
        if (refreshTokenHeader != null && refreshTokenHeader.startsWith("Bearer ")) {
            String refreshToken = refreshTokenHeader.substring(7);
            if (jwtService.validateToken(refreshToken, user)) {
                String newAccessToken = jwtService.generateToken(user2);
                response.setHeader("Authorization", "Bearer " + newAccessToken);
                // Yeni access token ile giriş yapılacak
                createAuthenticationToken(request, user, username, user2);
            }
        }
    }

    private void createAuthenticationToken(HttpServletRequest request, UserDetails user, String username, User user2) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,
                        null,
                        user.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}