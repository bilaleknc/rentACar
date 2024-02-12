package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.repositories.UserRepository;
import com.tobeto.pair9.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }

    @Override
    public boolean isExistUserById(Integer id) {
        return userRepository.existsById(id);
    }
}
