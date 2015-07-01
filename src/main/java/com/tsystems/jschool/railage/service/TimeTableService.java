package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.TimeTableDao;
import com.tsystems.jschool.railage.domain.TimeTableLine;

import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class TimeTableService {

    private static TimeTableDao timeTableDao = new TimeTableDao();

    public static List<TimeTableLine> findByStationId(Integer stationId){
        return timeTableDao.findByStationId(stationId);
    }
}
