package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.RouteDao;
import com.tsystems.jschool.railage.domain.*;
import com.tsystems.jschool.railage.view.servlets.helpers.RouteFormParams;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudolph on 02.07.15.
 */
public class RouteService {

    private static RouteDao routeDao = new RouteDao();

    public static List<Route> findAllRoutes(){
        return routeDao.findAll();
    }

    public static void addRoute(RouteFormParams params){

        Route route = new Route();
        List<Station> stations = createStations(params);
        List<TimeTableLine> timeTableLines = createTimeTableLine(route,stations,params);
        List<RoutePart> routeParts = createRouteParts(route,stations);
        //List<RoutePart> routeParts = createRouteParts(params);
        /*
        for (RoutePart routePart : routeParts ){
            routePart.setRoute(route);
        }
        */
        Integer trainId = params.getTrainId();
        Train train = TrainService.findTrainById(trainId);
        Train mergedTrain = TrainService.merge(train);
        for(TimeTableLine line : timeTableLines){
            line.setTrain(mergedTrain);
        }
        route.setRouteParts(routeParts);


        route.setTrain(mergedTrain);
        routeDao.merge(route);
        TimeTableService.merge(timeTableLines);


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

    private static List<Station> createStations(RouteFormParams params){
        List<Station> stations = new ArrayList<>();
        for (Integer stationId : params.getStationsIds()){

            Station station = StationService.findStationById(stationId);
            stations.add(station);
        }
        return stations;
    }

    private static List<RoutePart> createRouteParts(Route route,List<Station> stations) {

        //List<Station> stations = new ArrayList<>();
        //List<TimeTableLine> timeTableLines = new ArrayList<>();
        List<TimeTableLine> timeTableLines = route.getTimeTableLines();

        /*
        for (Integer stationId : params.getStationsIds()){

            Station station = StationService.findStationById(stationId);
            stations.add(station);
        }
        */

        /*
        for (String time : params.getTimes()){

            TimeTableLine timeTableLine = new TimeTableLine();
            TimeInfo info = new TimeInfo();
            info.setDepartureTime(Time.valueOf(time + ":00"));
            info.setPeriodicInfo(true);
            info.setPeriod(params.getPeriod());
            timeTableLine.setTimeInfo(info);
            timeTableLines.add(timeTableLine);
        }
        */
        /*
        for (int i = 0 ; i < stations.size(); i++){
            stations.get(i).addTimeTableLine(timeTableLines.get(i));
            //timeTableLines.get(i).setRoute(route);
        }
        */

        List<RoutePart> routeParts = new ArrayList<>();
        int last = stations.size() - 1;
        for (int i = 0; i < stations.size(); i++){
            RoutePart routePart;
            if(i == 0){
                routePart = new RoutePart(
                        stations.get(i),RoutePartStatuses.START.value(),i + 1);
            }
            else if(i == last){
                routePart = new RoutePart(
                        stations.get(i),RoutePartStatuses.END.value(),i + 1);
            }
            else {
                routePart = new RoutePart(
                        stations.get(i),RoutePartStatuses.STAND.value(),i + 1);
            }
            routeParts.add(routePart);
        }

        for (RoutePart routePart : routeParts ){
            routePart.setRoute(route);
        }

        return routeParts;
    }
}
