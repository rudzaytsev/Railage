package com.tsystems.jschool.railage.domain;

import java.util.List;

/**
 * Represents train route in information system's
 * domain. Route relates to train and consists of
 * 2 or more route parts
 * @author Rudolph Zaytsev
 */
public class Route extends DomainObject {

    /** train that corresponds to route */
    private Train train;

    /** list of parts of the route */
    private List<RoutePart> routeParts;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<RoutePart> getRouteParts() {
        return routeParts;
    }

    public void setRouteParts(List<RoutePart> routeParts) {
        this.routeParts = routeParts;
    }
}
