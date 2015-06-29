package com.tsystems.jschool.railage.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Represents train ride in information system's domain.
 * Every train ride has train and tickets for ride.
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "TrainRides")
public class TrainRide extends DomainObject {

    /** train for ride */
    @ManyToOne
    @JoinColumn(name = "trainId")
    private Train train;

    /** tickets for ride */
    @OneToMany(mappedBy = "trainRide")
    private List<Ticket> tickets;
    
    /** train route for ride */
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    /** date of train ride */
    private Date date;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
