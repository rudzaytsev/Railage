package com.tsystems.jschool.railage.datasource.interfaces;

import com.tsystems.jschool.railage.domain.TimeTableLine;

import java.util.List;

/**
 * Created by rudolph on 31.07.15.
 */
public interface TimeTableDao extends Dao<TimeTableLine, Integer> {
    @Override
    TimeTableLine merge(TimeTableLine entity);

    @Override
    TimeTableLine findById(Integer id);

    @Override
    List<TimeTableLine> findAll();

    List<TimeTableLine> findByStationId(Integer stationId);
}
