package com.tsystems.jschool.railage.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by rudolph on 09.08.15.
 */
public class UserAdapter extends User {

    private Integer balance;


    public UserAdapter(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer balance) {
        super(username, password, authorities);
        this.balance = balance;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
