package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.interfaces.TimeTableDao;
import com.tsystems.jschool.railage.domain.TimeTableLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
@Service
@Transactional(readOnly = true)
public class TimeTableService {

    @Autowired
    private TimeTableDao timeTableDao;

    public List<TimeTableLine> findByStationId(Integer stationId){
        return timeTableDao.findByStationId(stationId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,
    rollbackFor = Exception.class)
    public List<TimeTableLine> merge(List<TimeTableLine> timeTableLines){

        List<TimeTableLine> result = new ArrayList<>();
        for (TimeTableLine line : timeTableLines) {
            TimeTableLine resPart = timeTableDao.merge(line);
            result.add(resPart);
        }
        return result;
    }
}
