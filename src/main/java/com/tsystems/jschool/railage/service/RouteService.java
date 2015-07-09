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

    private RouteDao routeDao = new RouteDao();


    public Route findRouteById(Integer id){

        routeDao.open();
        Route route = null;
        try {
           route = routeDao.findById(id);
        }
        finally {
            routeDao.close();
        }
        return route;
    }

    public List<Route> findAllRoutes(){

        routeDao.open();
        List<Route> routes;
        try {
            routes = routeDao.findAll();
        }
        finally {
            routeDao.close();
        }
        return routes;
    }

    public void addRoute(RouteFormParams params){

        routeDao.open();
        try {

            Route route = new Route();
            List<Station> stations = createStations(params);
            List<TimeTableLine> timeTableLines = createTimeTableLine(route, stations, params);
            List<RoutePart> routeParts = createRouteParts(route, stations);
            TrainService trainService = new TrainService();

            Integer trainId = params.getTrainId();

            Train train = trainService.findTrainById(trainId);
            for (TimeTableLine line : timeTableLines) {
                line.setTrain(train);
            }
            route.setRouteParts(routeParts);
            route.setTimeTableLines(timeTableLines);
            route.setTrain(train);
            routeDao.merge(route);
        }
        finally {
            routeDao.close();
        }

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
        StationService stationService = new StationService();
        List<Station> stations = new ArrayList<>();
        for (Integer stationId : params.getStationsIds()){

            Station station = stationService.findStationById(stationId);
            stations.add(station);
        }
        return stations;
    }

    private static List<RoutePart> createRouteParts(Route route,List<Station> stations) {

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
}
