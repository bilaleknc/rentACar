package com.tobeto.pair9.services.rules;

import com.tobeto.pair9.core.utilities.exceptions.UserBusinessException;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.entities.concretes.User;
import com.tobeto.pair9.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserBusinessRules {

    private final UserRepository userRepository;

    public void findUserById(Integer id){
        if(!userRepository.existsById(id)){
            throw new UserBusinessException(Messages.userIsNotFound);
        }
    }

    public void isExistUserByEmail(String email){
        if(userRepository.existsUserByEmail(email)){
            throw new UserBusinessException(Messages.userAlreadySaved);
        }
    }

    public User getUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UserBusinessException(Messages.userIsNotFound);
        }
        return user.get();
    }
}
