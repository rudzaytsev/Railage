package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.UserDao;
import com.tsystems.jschool.railage.domain.Role;
import com.tsystems.jschool.railage.domain.User;
import com.tsystems.jschool.railage.service.helper.Pair;
import com.tsystems.jschool.railage.service.helper.Triple;

/**
 * Created by rudolph on 24.06.15.
 */
public class UserService {


    private static UserDao userDao = new UserDao();

    public static Triple<Boolean,Integer,String> logInUser(String login, String password){
        User user = userDao.findUserByParams(login, password);
        return (user != null) ? new Triple(true,user.getId(),user.getRole()) :
                                new Triple(false,null,null);
    }

    public static boolean isValidUserData(User user){
        return isValidLogin(user.getLogin()) &&
               isValidPassword(user.getPassword());
    }

    public static boolean isValidLogin(String login){
        return login != null && login.matches("^[a-zA-Z]+[0-9a-zA-Z]*$");
    }

    public static boolean isValidPassword(String password){
        return password != null && !password.isEmpty();
    }

    public static boolean isValidRole(String userRole){
        for (Role role : Role.values()){
            if (userRole.equals(role.toString())){
                return true;
            }
        }
        return false;
    }


}
