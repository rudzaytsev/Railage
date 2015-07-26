package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.UserDao;
import com.tsystems.jschool.railage.domain.Role;
import com.tsystems.jschool.railage.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rudolph on 24.06.15.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserDao userDao;


    public User findUser(String login, String password){
        User user;
        user = userDao.findUserByParams(login,password);
        return user;
    }


    public boolean isValidUserData(User user){
        return isValidLogin(user.getLogin()) &&
               isValidPassword(user.getPassword()) &&
                isValidRole(user.getRole());
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


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void addUser(User user){
        userDao.persist(user);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Integer createUser(User user){
        Integer id = null;
        addUser(user);
        User createdUser = userDao.findUserByParams(
                                user.getLogin(),user.getPassword());
        id = createdUser.getId();
        return id;
    }

}
