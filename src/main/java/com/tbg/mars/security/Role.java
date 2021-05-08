package com.tbg.mars.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER;

    public String getAuthority() {
        return name();
    }

}
