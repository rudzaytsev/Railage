package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.domain.TimeTableLine;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TimeTableService;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectParameterException;
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

    @Autowired
    ControllersUtils controllersUtils;

    private static org.apache.log4j.Logger logger =
            org.apache.log4j.Logger.getLogger(StationsController.class);

    @RequestMapping(value="/stations/all", method = RequestMethod.GET)
    public String showAllStations(Model model){

        controllersUtils.addStations2Model(model);
        logger.info("Show all stations");
        return Pages.STATIONS;
    }

    @RequestMapping(value = "/add/station", method = RequestMethod.POST)
    public String addStation(String stationName, Model model){

        try {
            stationService.addStation(stationName);

        } catch (IncorrectParameterException |
                 DomainObjectAlreadyExistsException e) {

            String errorMsg = "Can not add Station. " + e.getMessage();
            controllersUtils.addErrorMessage(model, errorMsg);
            logger.error(errorMsg);
            return Pages.STATIONS;
        }
        finally {
            controllersUtils.addStations2Model(model);
        }
        String infoMsg = "Station added.";
        controllersUtils.addSuccessMessage(model,infoMsg);
        logger.info(infoMsg);
        return Pages.STATIONS;

    }

    @RequestMapping(value="/timetable/station/{stationId}", method = RequestMethod.GET)
    public String showTimeTable(@PathVariable("stationId") Integer stationId, Model model){

        List<TimeTableLine> timeTableLines = timeTableService.findByStationId(stationId);
        model.addAttribute(Utils.TIMETABLE, timeTableLines);
        model.addAttribute(Utils.CURRENT_STATION, stationService.findStationById(stationId));
        logger.info("Show timetable for station with stationId = " + stationId);
        return Pages.TIMETABLE;
    }
}
