package com.tsystems.jschool.railage.domain;

/**
 * Represents ticket to train ride in
 * information system domain.
 * Tickets are buying by passengers to make train rides.
 * @author Rudolph Zaytsev
 */
public class Ticket extends DomainObject {

    /** passenger, who bought ticket */
    private Passenger passenger;

    /** train ride, which corresponds to ticket */
    private TrainRide trainRide;

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
}
