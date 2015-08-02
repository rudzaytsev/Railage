package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.Ticket;

import java.util.List;

/**
 * Created by rudolph on 02.08.15.
 */
public interface TicketDao extends Dao<Ticket, Integer> {
    @Override
    Ticket merge(Ticket entity);

    @Override
    Ticket findById(Integer id);

    @Override
    List<Ticket> findAll();

    Long countTicketsByRide(Integer rideId);
}
