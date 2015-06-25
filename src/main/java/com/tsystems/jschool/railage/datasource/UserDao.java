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
public class UserDao extends JpaDao implements Dao<User,Integer> {



    @Override
    public final void persist(final User entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        }
        finally {

            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

    }

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
        String queryStr =
           "SELECT u FROM User u WHERE u.login = ?1 AND u.password = ?2";
        TypedQuery<User> query = entityManager.createQuery(queryStr, User.class);
        query.setParameter(1, user.getLogin());
        query.setParameter(2, user.getPassword());
        try {
            User registred = query.getSingleResult();
        }
        catch(NoResultException e){
            return false;
        }
        finally {

        }
        return true;

    }
}
