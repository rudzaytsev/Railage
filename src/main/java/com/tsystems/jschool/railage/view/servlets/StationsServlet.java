package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.domain.Station;
import com.tsystems.jschool.railage.domain.TimeTableLine;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TimeTableService;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
public class StationsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        Integer stationId = Utils.extractId(uri);
        if(stationId != null){

            HttpSession session = request.getSession();
            List<TimeTableLine> timeTableLines = TimeTableService.findByStationId(stationId);
            session.setAttribute(Utils.TIMETABLE, timeTableLines);
            response.sendRedirect(Pages.TIMETABLE);
            return;
        }
        else {

            HttpSession session = request.getSession();
            List<Station> stations = StationService.findAllStations();
            session.setAttribute(Utils.STATIONS, stations);
            response.sendRedirect(Pages.STATIONS);
            return;
        }

    }
}
