package com.tsystems.jschool.railage.datasource.interfaces;

import com.tsystems.jschool.railage.domain.User;

import java.util.List;

/**
 * Created by rudolph on 26.07.15.
 */
public interface UserDao extends Dao<User, Integer> {
    @Override
    User merge(User entity);

    @Override
    User findById(Integer id);

    @Override
    List<User> findAll();

    User findUserByParams(String login, String password);

    User findUserByLogin(String login);
}
