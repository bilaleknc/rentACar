package com.tobeto.pair9.entities.concretes;

import org.springframework.security.core.GrantedAuthority;

public enum Role  implements GrantedAuthority {

    USER,
    ADMIN,
    MODERATOR,
    CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
