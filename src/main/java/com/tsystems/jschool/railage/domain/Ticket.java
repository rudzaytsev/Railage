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
    @ManyToOne
    @JoinColumn(name = "passengerId")
    private Passenger passenger;

    /** train ride, which corresponds to ticket */
    @ManyToOne
    @JoinColumn(name = "trainRideId")
    private TrainRide trainRide;

    /**
     * boarding railway station for buying ticket
     * and starting travel
     */
    @OneToOne
    @JoinColumn(name = "boardingStationId")
    private Station boardingStation;

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
}
