package com.tobeto.pair9.entities.concretes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("Admin:read"),
    ADMIN_UPDATE("Admin:update"),
    ADMIN_CREATE ("Admin:add"),
    ADMIN_DELETE("Admin:delete"),

    USER_READ("User:read");


    @Getter
    private final String permission;


}
