package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.domain.Period;
import com.tsystems.jschool.railage.service.RouteService;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.exceptions.DuplicatedStationsInRouteException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectStationsDepartureTimesOrderException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectTimeFormatException;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.servlets.helpers.RouteFormParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by rudolph on 05.07.15.
 */
public class RouteBuilderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.addRoute(request,response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute(Utils.STATIONS, StationService.findAllStations());
        session.setAttribute(Utils.TRAINS, TrainService.findAllTrains());
        session.setAttribute(Utils.PERIODS, Period.getPeriodsAsList());
        response.sendRedirect(Pages.ROUTE_BULDER);
        return;
    }

    private void addRoute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RouteFormParams params = new RouteFormParams();
        params.fill(request);
        try {
            params.validate();
            RouteService.addRoute(params);

        } catch (IncorrectStationsDepartureTimesOrderException |
                 IncorrectTimeFormatException |
                 DuplicatedStationsInRouteException e) {

           String errorMsg = "Error! " + e.getMessage();
           request.getSession().setAttribute(Utils.IS_VALIDATION_ERR,true);
           request.getSession().setAttribute(Utils.VALIDATION_ERROR_MSG,errorMsg);
        }
        request.getSession().setAttribute(Utils.ROUTES, RouteService.findAllRoutes());
        response.sendRedirect(Pages.ROUTES);

    }
}
