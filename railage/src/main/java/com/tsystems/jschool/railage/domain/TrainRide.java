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
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "trainId")
    private Train train;

    /** tickets for ride */
    @OneToMany(mappedBy = "trainRide",cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private List<Ticket> tickets;
    
    /** train route for ride */
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "routeId")
    private Route route;

    /** date of train ride */
    @Column(name = "rideDate")
    private Date date;

    /** ride's price in $ **/
    private Integer price;

    public TrainRide(){
        //does nothing
    }

    public TrainRide(Route route, Date date, Train train) {
        this.route = route;
        this.date = date;
        this.train = train;
    }

    public TrainRide(Route route, Date date, Train train, Integer price) {
        this(route,date,train);
        this.price = price;
    }

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TrainRide{" +
                "train=" + train +
                ", tickets=" + tickets +
                ", route=" + route +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
