package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 12.07.15.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class TicketDaoImpl extends JpaDao<Ticket> implements TicketDao {

    @Override
    public Ticket findById(Integer id) {
        String queryStr = "SELECT t FROM Ticket t WHERE t.id = ?1";
        TypedQuery<Ticket> query = entityManager.createQuery(queryStr, Ticket.class);
        query.setParameter(1,id);
        Ticket requiredTicket = null;
        try {
            requiredTicket = query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return requiredTicket;
    }

    @Override
    public List<Ticket> findAll() {
        String queryStr = "SELECT t FROM Ticket t";
        TypedQuery<Ticket> query = entityManager.createQuery(queryStr, Ticket.class);
        return query.getResultList();
    }

    @Override
    public Long countTicketsByRide(Integer rideId){

        String queryStr = "SELECT COUNT(t) FROM Ticket t WHERE t.trainRide.id = ?1";
        TypedQuery<Long> query = entityManager.createQuery(queryStr,  Long.class);
        query.setParameter(1,rideId);
        Long numberOfTickets = 0l;
        try {
            numberOfTickets = query.getSingleResult();
        }
        catch(NoResultException e){
            return 0l;
        }
        return numberOfTickets;
    }
}
