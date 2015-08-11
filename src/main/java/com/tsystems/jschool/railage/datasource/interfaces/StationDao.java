package com.tsystems.jschool.railage.datasource.interfaces;

import com.tsystems.jschool.railage.domain.Station;

import java.util.List;

/**
 * Created by rudolph on 29.07.15.
 */
public interface StationDao extends Dao<Station, Integer> {
    @Override
    Station merge(Station entity);

    @Override
    Station findById(Integer id);

    @Override
    List<Station> findAll();

    List<Station> findStationsByName(String stationName);
}
