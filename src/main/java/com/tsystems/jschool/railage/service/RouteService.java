package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.RouteDao;
import com.tsystems.jschool.railage.domain.Route;

import java.util.List;

/**
 * Created by rudolph on 02.07.15.
 */
public class RouteService {

    private static RouteDao routeDao = new RouteDao();

    public static List<Route> findAllRoutes(){
        return routeDao.findAll();
    }
}
