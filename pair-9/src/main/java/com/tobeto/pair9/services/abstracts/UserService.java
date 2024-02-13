package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.entities.concretes.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean isExistUserByUserName(String userName);

    //  with username
    User getUser(String username);

}
