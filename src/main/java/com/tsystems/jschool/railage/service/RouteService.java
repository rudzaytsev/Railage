package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.RouteDao;
import com.tsystems.jschool.railage.datasource.StationDao;
import com.tsystems.jschool.railage.datasource.TrainDao;
import com.tsystems.jschool.railage.domain.*;
import com.tsystems.jschool.railage.service.exceptions.DuplicatedStationsInRouteException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectStationsDepartureTimesOrderException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectTimeFormatException;
import com.tsystems.jschool.railage.view.controllers.helpers.RouteFormParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rudolph on 02.07.15.
 */
@Service
@Transactional(readOnly = true)
public class RouteService {


    private static final int MINUTES_PER_HOUR = 60;

    private static final int HOURS_UPPER_BOUND = 23;

    private static final int HOURS_LOWER_BOUND = 0;

    private static final int MINUTES_UPPER_BOUND = 59;

    private static final int MINUTES_LOWER_BOUND = 0;

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private StationDao stationDao;


    public Route findRouteById(Integer id){
        return routeDao.findById(id);
    }

    public List<Route> findAllRoutes(){
        return routeDao.findAll();
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void addRoute(RouteFormParams params){

        Route route = new Route();
        List<Station> stations = createStations(params);
        List<TimeTableLine> timeTableLines = createTimeTableLine(route, stations, params);
        List<RoutePart> routeParts = createRouteParts(route, stations);

        Integer trainId = params.getTrainId();

        Train train = trainDao.findById(trainId);
        for (TimeTableLine line : timeTableLines) {
            line.setTrain(train);
        }
        route.setRouteParts(routeParts);
        route.setTimeTableLines(timeTableLines);
        route.setTrain(train);
        routeDao.merge(route);

    }

    private static List<TimeTableLine> createTimeTableLine(Route route,List<Station> stations,RouteFormParams params){
        List<TimeTableLine> timeTableLines = new ArrayList<>();
        int i = 0;

        for (String time : params.getTimes()){

            TimeTableLine timeTableLine = new TimeTableLine();
            TimeInfo info = new TimeInfo();
            info.setDepartureTime(Time.valueOf(time + ":00"));
            info.setPeriodicInfo(true);
            info.setPeriod(params.getPeriod());
            timeTableLine.setTimeInfo(info);
            timeTableLine.setRoute(route);
            timeTableLine.setStation(stations.get(i));
            stations.get(i).addTimeTableLine(timeTableLine);
            timeTableLines.add(timeTableLine);
            i++;
        }
        return timeTableLines;
    }

    private List<Station> createStations(RouteFormParams params){

        List<Station> stations = new ArrayList<>();
        for (Integer stationId : params.getStationsIds()){

            Station station = stationDao.findById(stationId);
            stations.add(station);
        }
        return stations;
    }

    private List<RoutePart> createRouteParts(Route route,List<Station> stations) {

        List<TimeTableLine> timeTableLines = route.getTimeTableLines();
        List<RoutePart> routeParts = new ArrayList<>();
        int last = stations.size() - 1;
        for (int i = 0; i < stations.size(); i++){
            RoutePart routePart;
            if (i == 0){
                routePart = new RoutePart(
                        stations.get(i),RoutePartStatuses.START.value(),i + 1);
            }
            else if (i == last){
                routePart = new RoutePart(
                        stations.get(i),RoutePartStatuses.END.value(),i + 1);
            }
            else {
                routePart = new RoutePart(
                        stations.get(i),RoutePartStatuses.STAND.value(),i + 1);
            }
            routeParts.add(routePart);
        }

        for (RoutePart routePart : routeParts){
            routePart.setRoute(route);
        }

        return routeParts;
    }

    private void validateStations(RouteFormParams params) throws DuplicatedStationsInRouteException {

        List<Integer> stationsIds = params.getStationsIds();
        Set<Integer> uniqueStationIds = new HashSet<>(stationsIds);
        boolean hasDuplicatedStationIds = uniqueStationIds.size() != stationsIds.size();
        if(hasDuplicatedStationIds){
            throw new DuplicatedStationsInRouteException(
                    "Route must contains only unique stations");
        }

    }

    private void validateTimes(RouteFormParams params) throws IncorrectTimeFormatException, IncorrectStationsDepartureTimesOrderException {

        List<String> times = params.getTimes();
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

    public void validate(RouteFormParams params)
            throws IncorrectStationsDepartureTimesOrderException,
            IncorrectTimeFormatException, DuplicatedStationsInRouteException {

        validateStations(params);
        validateTimes(params);
    }

}
