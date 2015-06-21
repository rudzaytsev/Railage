package com.tsystems.jschool.railage.domain;

/**
 * Represents ticket to train ride in
 * information system domain.
 * Tickets are buying by passengers to make train rides
 * from boarding railway station.
 * @author Rudolph Zaytsev
 */
public class Ticket extends DomainObject {

    /** passenger, who bought ticket */
    private Passenger passenger;

    /** train ride, which corresponds to ticket */
    private TrainRide trainRide;

    /**
     * boarding railway station for buying ticket
     * and starting travel
     */
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
