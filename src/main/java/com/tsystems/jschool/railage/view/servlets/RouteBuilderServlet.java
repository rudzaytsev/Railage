package com.tsystems.jschool.railage.view.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        /*
        TrainService trainService = new TrainService();
        StationService stationService = new StationService();

        HttpSession session = request.getSession();
        session.setAttribute(Utils.STATIONS, stationService.findAllStations());
        session.setAttribute(Utils.TRAINS, trainService.findAllTrains());
        session.setAttribute(Utils.PERIODS, Period.getPeriodsAsList());
        response.sendRedirect(Pages.ROUTE_BULDER);
        */
        return;
    }

    private void addRoute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*
        RouteFormParams params = new RouteFormParams();
        params.fill(request);
        RouteService routeService = new RouteService();
        try {
            params.validate();

            routeService.addRoute(params);

        } catch (IncorrectStationsDepartureTimesOrderException |
                 IncorrectTimeFormatException |
                 DuplicatedStationsInRouteException e) {

           String errorMsg = "Error! " + e.getMessage();
           request.getSession().setAttribute(Utils.IS_VALIDATION_ERR,true);
           request.getSession().setAttribute(Utils.VALIDATION_ERROR_MSG,errorMsg);
        }
        request.getSession().setAttribute(Utils.ROUTES, routeService.findAllRoutes());
        response.sendRedirect(Pages.ROUTES);
        */
    }
}
