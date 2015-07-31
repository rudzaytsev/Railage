package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TimeTableService;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    public String showTimeTable(Integer stationId, Model model){


        /*
        String uri = request.getRequestURI();

        Integer stationId = Utils.extractId(uri);
        if(stationId != null){

            HttpSession session = request.getSession();
            StationService stationService = new StationService();
            TimeTableService timeTableService = new TimeTableService();
            List<TimeTableLine> timeTableLines = timeTableService.findByStationId(stationId);
            session.setAttribute(Utils.TIMETABLE, timeTableLines);
            session.setAttribute(Utils.CURRENT_STATION,stationService.findStationById(stationId));
            response.sendRedirect(Pages.TIMETABLE);
            return;
        }
        else {
            this.processStations(request, response);
            return;
        }
        */

        return Pages.TIMETABLE;
    }
}
