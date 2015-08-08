package com.tsystems.jschool.railage.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by rudolph on 08.08.15.
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
