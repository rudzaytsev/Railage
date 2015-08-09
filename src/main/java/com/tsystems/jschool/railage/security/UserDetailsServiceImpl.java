package com.tsystems.jschool.railage.security;

import com.tsystems.jschool.railage.domain.User;
import com.tsystems.jschool.railage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudolph on 08.08.15.
 */
@Service("customUserDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    User appUser;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User appUser = userService.findUserByLogin(login);
        if(appUser == null){
            throw new UsernameNotFoundException("No such user " + login);
        }
        if(appUser.getRole().isEmpty()){
            throw new UsernameNotFoundException(
                    "Username " + login + " has no authorities");
        }
        return new UserAdapter(appUser.getLogin(),appUser.getPassword(),
                            getGrantedAuthorities(appUser),appUser.getBalance());
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthorityImpl(user.getRole()));

        return authorities;
    }
}
