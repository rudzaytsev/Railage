package com.tsystems.jschool.railage.datasource.interfaces;

import com.tsystems.jschool.railage.domain.Route;

import java.util.List;

/**
 * Created by rudolph on 29.07.15.
 */
public interface RouteDao extends Dao<Route, Integer> {
    @Override
    Route merge(Route entity);

    @Override
    Route findById(Integer id);

    @Override
    List<Route> findAll();
}
