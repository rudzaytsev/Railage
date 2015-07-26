package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.UserDao;
import com.tsystems.jschool.railage.domain.Role;
import com.tsystems.jschool.railage.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rudolph on 24.06.15.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public User findUser(String login, String password){
        User user;
        user = userDao.findUserByParams(login,password);
        return user;
    }

    /*
    public Triple<Boolean,Integer,String> logInUser(String login, String password){
        User user;
        userDao.open();
        try {
            user = userDao.findUserByParams(login, password);
        }
        finally {
            userDao.close();
        }
        return (user != null) ? new Triple(true,user.getId(),user.getRole()) :
                                new Triple(false,null,null);
    }
    */

    public boolean isValidUserData(String login,String password, String role){
        return isValidLogin(login) && isValidPassword(password) && isValidPassword(role);
    }

    public boolean isValidLogin(String login){
        return login != null && login.matches("^[a-zA-Z]+[0-9a-zA-Z]*$");
    }

    public boolean isValidPassword(String password){
        return password != null && !password.isEmpty();
    }

    public boolean isValidRole(String userRole){
        for (Role role : Role.values()){
            if (userRole.equals(role.toString())){
                return true;
            }
        }
        return false;
    }

    public boolean addUser(String login, String password, String role){

        boolean done = true;
        userDao.open();
        try {
            User user = new User(login, password, role);
            userDao.persist(user);
        }
        catch(Exception e){
            done = false;
        }
        finally {
            userDao.close();
        }
        return done;
    }

    public Integer createUser(String login, String password, String role){
       Integer id = null;
        boolean added = addUser(login,password,role);
       if(added){
           try {
               userDao.open();
               User user = userDao.findUserByParams(login, password);
               id = user.getId();
           }
           finally {
               userDao.close();
           }
       }
       return id;
    }


}
