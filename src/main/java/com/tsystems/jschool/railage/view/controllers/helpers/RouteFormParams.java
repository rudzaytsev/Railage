package com.tsystems.jschool.railage.view.controllers.helpers;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudolph on 06.07.15.
 */
public class RouteFormParams {

    public static final int MAX_ROUTE_PARTS_NUMBER = 21;



    private List<Integer> stationsIds = new ArrayList<>();

    private List<String> times = new ArrayList<>();

    private Integer trainId;

    private String period;

    public List<Integer> getStationsIds() {
        return stationsIds;
    }

    public void setStationsIds(List<Integer> stationsIds) {
        this.stationsIds = stationsIds;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public Integer getTrainId() {
        return trainId;
    }


    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


    public void fill(HttpServletRequest request){

        trainId = Integer.parseInt(request.getParameter("trainId"));
        period = request.getParameter("period");

        for (int i = 1; i <= MAX_ROUTE_PARTS_NUMBER; i++){
           try {
               int stationId = Integer.parseInt(request.getParameter("stationId" + i));
               stationsIds.add(stationId);
               String time = request.getParameter("departureTime" + i).trim();
               times.add(time);
           }
           catch(NumberFormatException e){
               //if nothing was parsed, finish fill params
               return;
           }

        }
    }

    /*
    public void validateStations() throws DuplicatedStationsInRouteException {

        Set<Integer> uniqueStationIds = new HashSet<>(stationsIds);
        boolean hasDuplicatedStationIds = uniqueStationIds.size() != stationsIds.size();
        if(hasDuplicatedStationIds){
            throw new DuplicatedStationsInRouteException(
                    "Route must contains only unique stations");
        }

    }

    public void validateTimes() throws IncorrectTimeFormatException, IncorrectStationsDepartureTimesOrderException {

        List<Integer> timesInMinutes = new ArrayList<>();
        for (String time : times){
            String[] hoursAndMinutes = time.split(":",2);
            if (hoursAndMinutes.length < 2){
                throw new IncorrectTimeFormatException(
                        "Incorrect Format of departure time. Should be hh:mm .Example 18:30");
            }
            try {
                int hours = Integer.parseInt(hoursAndMinutes[0]);
                int minutes = Integer.parseInt(hoursAndMinutes[1]);

                if (hours > HOURS_UPPER_BOUND || hours < HOURS_LOWER_BOUND){
                    throw new IncorrectTimeFormatException(
                         "Incorrect Format of departure time. Hours must be in range from 0 to 23");
                }
                if (minutes > MINUTES_UPPER_BOUND || minutes < MINUTES_LOWER_BOUND ){
                    throw new IncorrectTimeFormatException(
                            "Incorrect Format of departure time. Minutes must be in range from 0 to 59");
                }
                timesInMinutes.add(hours*MINUTES_PER_HOUR + minutes);


            }
            catch(NumberFormatException e){
                throw new IncorrectTimeFormatException(
                      "Incorrect Format of departure time. Hours and minutes should be integers. Example 18:30");
            }
        }
        if(!isSorted(timesInMinutes)){
            throw new IncorrectStationsDepartureTimesOrderException(
                    "Incorrect Order of Departure Times. Departure time should increase from start to end station"
            );
        }

    }

    private boolean isSorted(List<Integer> timesInMinutes){
        int i = 1;
        boolean isSorted = true;
        while( isSorted &&  i < timesInMinutes.size() ){
            int previous = timesInMinutes.get(i - 1);
            int current = timesInMinutes.get(i);
            if(previous >= current){
                isSorted = false;
            }
            ++i;
        }
        return isSorted;

    }

    public void validate()
            throws IncorrectStationsDepartureTimesOrderException,
                   IncorrectTimeFormatException, DuplicatedStationsInRouteException {

        validateStations();
        validateTimes();
    }
    */


}
