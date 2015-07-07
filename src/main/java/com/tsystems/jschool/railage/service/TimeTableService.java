package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.TimeTableDao;
import com.tsystems.jschool.railage.domain.TimeTableLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class TimeTableService {

    private TimeTableDao timeTableDao = new TimeTableDao();

    public List<TimeTableLine> findByStationId(Integer stationId){

        List<TimeTableLine> timeTableLines;
        timeTableDao.open();
        try {
            timeTableLines = timeTableDao.findByStationId(stationId)
        }
        finally {
            timeTableDao.close();
        }
        return timeTableLines;
    }

    public List<TimeTableLine> merge(List<TimeTableLine> timeTableLines){

        timeTableDao.open();
        List<TimeTableLine> result = new ArrayList<>();
        try {
            for (TimeTableLine line : timeTableLines) {
                TimeTableLine resPart = timeTableDao.merge(line);
                result.add(resPart);
            }
        }
        finally {
            timeTableDao.close();
        }
        return result;
    }
}
