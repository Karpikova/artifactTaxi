package main.controllers;

import org.springframework.security.core.GrantedAuthority;

/*
 * ${Classname}
 *
 * Version 1.0
 *
 * 01.05.2017
 *
 * Karpikova
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String role_user;
    public GrantedAuthorityImpl(String role_user) {
        this.role_user = role_user;
    }

    public String getAuthority() {
        return null;
    }
}
