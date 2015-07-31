package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.domain.TimeTableLine;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TimeTableService;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by rudolph on 31.07.15.
 */
@Controller
public class StationsController {

    @Autowired
    StationService stationService;

    @Autowired
    TimeTableService timeTableService;

    @RequestMapping(value="/stations/all", method = RequestMethod.GET)
    public String showAllStations(Model model){

        model.addAttribute(Utils.STATIONS,stationService.findAllStations());
        return Pages.STATIONS;
    }

    @RequestMapping(value="/timetable/station/{stationId}", method = RequestMethod.GET)
    public String showTimeTable(@PathVariable("stationId") Integer stationId, Model model){

        List<TimeTableLine> timeTableLines = timeTableService.findByStationId(stationId);
        model.addAttribute(Utils.TIMETABLE, timeTableLines);
        model.addAttribute(Utils.CURRENT_STATION, stationService.findStationById(stationId));
        return Pages.TIMETABLE;
    }
}
