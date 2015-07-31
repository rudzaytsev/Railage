package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectParameterException;
import com.tsystems.jschool.railage.view.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rudolph on 01.07.15.
 */
public class StationsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.addStation(request,response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

    }

    private void addStation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String stationName = request.getParameter("stationName");
        StationService stationService = new StationService();
        try {
            stationService.addStation(stationName);

        } catch (IncorrectParameterException |
                 DomainObjectAlreadyExistsException e) {

            String errorMsg = "Can not add Station. " + e.getMessage();
            request.getSession().setAttribute(Utils.IS_VALIDATION_ERR,true);
            request.getSession().setAttribute(Utils.VALIDATION_ERROR_MSG,errorMsg);
            this.processStations(request, response);
            return;
        }
        request.getSession().setAttribute(Utils.SUCCESS,true);
        request.getSession().setAttribute(Utils.INFO_MSG,"Station added.");
        this.processStations(request, response);
    }

    private void processStations(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        HttpSession session = request.getSession();
        StationService stationService = new StationService();
        List<Station> stations = stationService.findAllStations();
        session.setAttribute(Utils.STATIONS, stations);
        response.sendRedirect(Pages.STATIONS);
        */
    }
}
