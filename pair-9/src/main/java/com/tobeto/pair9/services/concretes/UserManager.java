package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.entities.concretes.Role;
import com.tobeto.pair9.entities.concretes.User;
import com.tobeto.pair9.repositories.UserRepository;
import com.tobeto.pair9.services.abstracts.UserService;
import com.tobeto.pair9.services.dtos.user.requests.CreateUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
}
