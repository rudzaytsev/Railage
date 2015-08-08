package com.tsystems.jschool.railage.security;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rudolph on 08.08.15.
 */
public class UserAuthFilter extends UsernamePasswordAuthenticationFilter {

    private static final String  USERNAME_PARAM = "login";

    private static final String PASSWORD_PARAM = "password";


    @Override
    protected String obtainPassword(HttpServletRequest request) {
       String password = request.getParameter(PASSWORD_PARAM);
       if(password == null){
           password = "";
       }
       return password;
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String userName = request.getParameter(USERNAME_PARAM);
        if(userName == null){
            userName = "";
        }
        return userName;
    }
}
