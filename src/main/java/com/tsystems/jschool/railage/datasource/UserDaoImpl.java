package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 24.06.15.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl extends JpaDao<User> implements UserDao {

    @Override
    public final User merge(final User entity) {
        return null;
    }

    @Override
    public final User findById(final Integer id) {
        return null;
    }

    @Override
    public final List<User> findAll() {
        return null;
    }

    @Override
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

    @Override
    public User findUserByLogin(String login) {
        String queryStr =
                "SELECT u FROM User u WHERE u.login = ?1";
        TypedQuery<User> query = entityManager.createQuery(queryStr, User.class);
        query.setParameter(1,login);
        List<User> users;
        users = query.getResultList();
        if (users.isEmpty()){
            return null;
        }
        return users.get(0);
    }


}
