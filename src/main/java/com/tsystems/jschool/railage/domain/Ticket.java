package com.tsystems.jschool.railage.domain;

import javax.persistence.*;

/**
 * Represents ticket to train ride in
 * information system domain.
 * Tickets are buying by passengers to make train rides
 * from boarding railway station.
 * @author Rudolph Zaytsev
 */
@Entity
@Table(name = "Tickets")
public class Ticket extends DomainObject {

    /** passenger, who bought ticket */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "passengerId")
    private Passenger passenger;

    /** train ride, which corresponds to ticket */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "trainRideId")
    private TrainRide trainRide;

    /**
     * boarding railway station for buying ticket
     * and starting travel
     */
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "boardingStationId")
    private Station boardingStation;

    /**
     * payment for ticket in $
     */
    private Integer payment;

    public Ticket() {
        //does nothing
    }

    public Ticket(Passenger passenger, TrainRide trainRide, Station boardingStation) {
        this.passenger = passenger;
        this.trainRide = trainRide;
        this.boardingStation = boardingStation;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public TrainRide getTrainRide() {
        return trainRide;
    }

    public void setTrainRide(TrainRide trainRide) {
        this.trainRide = trainRide;
    }

    public Station getBoardingStation() {
        return boardingStation;
    }

    public void setBoardingStation(Station boardingStation) {
        this.boardingStation = boardingStation;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }
}
