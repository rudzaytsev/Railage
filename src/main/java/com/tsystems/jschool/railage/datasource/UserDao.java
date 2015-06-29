package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.DomainObject;
import com.tsystems.jschool.railage.domain.User;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 24.06.15.
 */
public class UserDao extends JpaDao<User> {

    @Override
    public final void update(final User entity) {

    }

    @Override
    public final User findById(final Integer id) {
        return null;
    }

    @Override
    public final List<User> findAll() {
        return null;
    }


    public boolean exists(User user){

        User existedUser = this.findUserByParams(
                                user.getLogin(),user.getPassword());
        return existedUser != null;
    }

    public User findUserByParams(String login, String password){
        String queryStr =
                "SELECT u FROM User u WHERE u.login = ?1 AND u.password = ?2";
        TypedQuery<User> query = entityManager.createQuery(queryStr, User.class);
        query.setParameter(1, login);
        query.setParameter(2, password);
        User registered = null;
        try {
            registered = query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return registered;
    }
}
