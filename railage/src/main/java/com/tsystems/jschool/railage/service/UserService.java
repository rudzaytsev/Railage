package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.interfaces.UserDao;
import com.tsystems.jschool.railage.domain.User;
import com.tsystems.jschool.railage.domain.enums.Role;
import com.tsystems.jschool.railage.security.UserAdapter;
import com.tsystems.jschool.railage.service.exceptions.*;
import com.tsystems.jschool.railage.view.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    @Qualifier("authManager")
    AuthenticationManager authManager;

    @Autowired
    //@Qualifier("encoder")
    PasswordEncoder passwordEncoder;


    public User findUser(String login, String password){
        User user;
        user = userDao.findUserByParams(login,password);
        return user;
    }

    public boolean authRegisteredUser(User user){
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(
                    user.getLogin(), user.getPlainPassword());

            Authentication result = authManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);

        } catch(AuthenticationException e) {
            return false;
        }
        return true;
    }


    public void isValidUserData(User user) throws InvalidUserDataException {
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validateRole(user.getRole());
    }

    private void validateLogin(String login) throws InvalidUserDataException {
        String loginPattern = "^[a-zA-Z]+[0-9a-zA-Z]*$";
        if (!login.matches(loginPattern)){
            throw new InvalidUserDataException(
                    "User login should contains only latin character or digits."
                    + " And fist symbol in login must be character"
            );
        }
    }

    public void validatePassword(String password) throws InvalidUserDataException {
        if(password.isEmpty()){
            throw new InvalidUserDataException(
                    "User password is empty string"
            );
        }
    }

    public void validateRole(String userRole) throws InvalidUserDataException {
        for (Role role : Role.values()){
            if (userRole.equals(role.toString())){
                return;
            }
        }
        throw new InvalidUserDataException("Invalid role");
    }


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void addUser(User user){
        hashUserPassword(user);
        userDao.persist(user);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void createUser(User user) throws DomainObjectAlreadyExistsException {
        User existedUser = userDao.findUserByLogin(user.getLogin());
        if (existedUser != null){
            throw new DomainObjectAlreadyExistsException(
                    "User with each login already exists"
            );
        }

        addUser(user);

    }

    public void hashUserPassword(User user){
        user.setPlainPassword(user.getPassword());
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
    }

    public User findUserByLogin(String login){
        return userDao.findUserByLogin(login);
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class)
    public void depositMoney(String login, Integer moneyAmount) throws UserNotFoundException, InvalidUserRoleException, OverflowWhileAdditionException {
        User user = this.findUserByLogin(login);
        if(user == null){
            throw new UserNotFoundException("User with login" + login + " doesnt exist");
        }
        else if(!user.getRole().equals(Role.ROLE_CLIENT.toString())){
            throw new InvalidUserRoleException("Only Clients may deposit money");
        }
        Utils.addWithOverflowCheck(user.getBalance().intValue(),
                                   moneyAmount.intValue());

        user.setBalance(user.getBalance() + moneyAmount);
        userDao.merge(user);
        UserAdapter springSecurityUserAdapter = this.getPrincipal();
        springSecurityUserAdapter.setBalance(user.getBalance());
    }

    public UserAdapter getPrincipal(){
        return (UserAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
