package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.repositories.UserRepository;
import com.tobeto.pair9.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private UserRepository userRepository;
    @Override
    public boolean existsId(int id) {
        return userRepository.existsById(id);
    }
}
