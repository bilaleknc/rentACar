package com.tobeto.pair9.services.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean isExistUserById(Integer id);

}
