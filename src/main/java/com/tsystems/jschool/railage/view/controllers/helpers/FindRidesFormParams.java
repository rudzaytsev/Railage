package com.tsystems.jschool.railage.view.controllers.helpers;

/**
 * Created by rudolph on 10.07.15.
 */
public class FindRidesFormParams {



    private String sourceStationId;

    private String destStationId;

    private String lowerBoundTime;

    private String upperBoundTime;

    //private TimeInterval interval;

    /*
    public void fill(HttpServletRequest request) throws IncorrectTimeIntervalException, IncorrectTimeFormatException {

        sourceStationId = Integer.parseInt(request.getParameter("fromStationId"));
        destStationId = Integer.parseInt(request.getParameter("toStationId"));
        String lowerBound = request.getParameter("lowerBoundTime");
        String upperBound = request.getParameter("upperBoundTime");
        this.validate(lowerBound,upperBound);
    }
    */

    /*
    private void validateTime(String time) throws IncorrectTimeFormatException {

        String[] hoursAndMinutes = time.split(":",2);
        if (hoursAndMinutes.length < 2){
            throw new IncorrectTimeFormatException(
                    "Incorrect Format of time. Should be hh:mm .Example 18:30");
        }
        try {
            int hours = Integer.parseInt(hoursAndMinutes[0]);
            int minutes = Integer.parseInt(hoursAndMinutes[1]);

            if (hours > HOURS_UPPER_BOUND || hours < HOURS_LOWER_BOUND){
                throw new IncorrectTimeFormatException(
                        "Incorrect Format of time. Hours must be in range from 0 to 23");
            }
            if (minutes > MINUTES_UPPER_BOUND || minutes < MINUTES_LOWER_BOUND ){
                throw new IncorrectTimeFormatException(
                        "Incorrect Format of time. Minutes must be in range from 0 to 59");
            }

        }
        catch (NumberFormatException e){
            throw new IncorrectTimeFormatException(
                    "Incorrect Format of time. Hours and minutes should be integers. Example 18:30");
        }

    }
    */

    /*
    private void validate(String lowerBoundTimeStr, String upperBoundTimeStr) throws IncorrectTimeIntervalException, IncorrectTimeFormatException {

        List<String> times = new ArrayList<>();
        times.add(lowerBoundTimeStr);
        times.add(upperBoundTimeStr);

        for(String timeStr : times){
            this.validateTime(timeStr);
        }

        Time lowerBoundTime = Time.valueOf(lowerBoundTimeStr.trim() + ADDITION_SECONDS);
        Time upperBoundTime = Time.valueOf(upperBoundTimeStr.trim() + ADDITION_SECONDS);
        this.interval = new TimeInterval(lowerBoundTime,upperBoundTime);

        boolean lowerBoundIsGreaterThanUpperBound =
                lowerBoundTime.getTime() >= upperBoundTime.getTime();
        if (lowerBoundIsGreaterThanUpperBound) {
            throw new IncorrectTimeIntervalException(
                    "Time upper bound should be greater than lower bound");
        }
    }
    */

    public String getSourceStationId() {
        return sourceStationId;
    }

    public String  getDestStationId() {
        return destStationId;
    }

    public void setSourceStationId(String sourceStationId) {
        this.sourceStationId = sourceStationId;
    }

    public void setDestStationId(String destStationId) {
        this.destStationId = destStationId;
    }

    public Integer getSourceStationIdAsInt(){
        return Integer.parseInt(sourceStationId);
    }

    public Integer getDestStationIdAsInt(){
        return Integer.parseInt(destStationId);
    }

    public String getLowerBoundTime() {
        return lowerBoundTime;
    }

    public void setLowerBoundTime(String lowerBoundTime) {
        this.lowerBoundTime = lowerBoundTime;
    }

    public String getUpperBoundTime() {
        return upperBoundTime;
    }

    public void setUpperBoundTime(String upperBoundTime) {
        this.upperBoundTime = upperBoundTime;
    }

    /*
    public TimeInterval getInterval() {
        return interval;
    }
    */
}
