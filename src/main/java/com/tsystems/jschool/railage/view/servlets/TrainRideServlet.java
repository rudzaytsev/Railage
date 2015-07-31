package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.domain.Passenger;
import com.tsystems.jschool.railage.service.PassengerService;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by rudolph on 29.06.15.
 */
@WebServlet(name = "TrainRideServlet")
public class TrainRideServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cmd = request.getParameter("cmd");
        if(Utils.CMD_FIND_RIDES.equals(cmd)){
            this.findTrainRides(request,response);
        }
        else {
            this.addTrainRide(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        Integer trainRideId = Utils.extractId(uri);
        PassengerService passengerService = new PassengerService();
        TrainService trainService = new TrainService();
        if (trainRideId == null) {
            String queryStr = request.getQueryString();
            if (queryStr != null) {

                Integer trainId = Utils.extractNumberParam(queryStr, "trainId");
                if (trainId != null) {
                    HttpSession session = request.getSession();
                    List<Passenger> passengers = passengerService.findPassengersByTrainId(trainId);

                    session.setAttribute(Utils.CURRENT_TRAIN,trainService.findTrainById(trainId));
                    session.setAttribute(Utils.HAS_CURRENT_TRAIN,true);
                    session.setAttribute(Utils.HAS_CURRENT_RIDE,false);
                    session.setAttribute(Utils.PASSENGERS, passengers);
                    response.sendRedirect(Pages.PASSENGERS);
                    return;
                }
            }

            this.processAllTrainRides(request,response);
            return;
        }
        else {
            HttpSession session = request.getSession();

            List<Passenger> passengers = passengerService.findPassengersByRideId(trainRideId);
            session.setAttribute(Utils.HAS_CURRENT_TRAIN,false);
            session.setAttribute(Utils.HAS_CURRENT_RIDE,true);
            session.setAttribute(Utils.CURRENT_TRAIN_RIDE,trainService.findTrainRideById(trainRideId));
            session.setAttribute(Utils.PASSENGERS, passengers);
            response.sendRedirect(Pages.PASSENGERS);
            return;
        }


    }

    private void addTrainRide(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        Integer routeId =  Integer.parseInt(request.getParameter("routeId"));
        String dateStr = request.getParameter("ride_date");
        TrainService trainService = new TrainService();
        try {
            trainService.addTrainRide(routeId, dateStr);
        }
        catch (ParseException | TimeTableConflictException e) {
            String errorMsg = "Error! " + e.getMessage();
            request.getSession().setAttribute(Utils.IS_VALIDATION_ERR,true);
            request.getSession().setAttribute(Utils.VALIDATION_ERROR_MSG,errorMsg);
            this.processAllTrainRides(request,response);
            return;
        }
        request.getSession().setAttribute(Utils.SUCCESS,true);
        request.getSession().setAttribute(Utils.INFO_MSG,"Train ride added!");
        this.processAllTrainRides(request,response);
        */
        return;
    }

    private void findTrainRides(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        try {
            FindRideFormParams params = new FindRideFormParams();
            params.fill(request);

            TrainService trainService = new TrainService();
            List<TrainRide> rides = trainService.findRidesBy(
                    params.getSourceStationId(),params.getDestStationId(),
                    params.getInterval());

            StationService stationService = new StationService();
            List<Station> stations = stationService.findAllStations();

            Station sourceStation = stationService.findStationById(
                                                params.getSourceStationId());

            Station destStation = stationService.findStationById(
                                                params.getDestStationId());


            RouteService routeService = new RouteService();

            request.getSession().setAttribute(Utils.IS_SEARCH_RESULT,true);
            request.getSession().setAttribute(Utils.SOURCE_STATION,sourceStation);
            request.getSession().setAttribute(Utils.DEST_STATION,destStation);
            request.getSession().setAttribute(Utils.TRAIN_RIDES,rides);
            request.getSession().setAttribute(Utils.STATIONS,stations);
            request.getSession().setAttribute(Utils.ROUTES, routeService.findAllRoutes());
            request.getSession().setAttribute(Utils.HAS_CURRENT_TRAIN,false);
            request.getSession().setAttribute(Utils.SUCCESS, true);

            if(!rides.isEmpty()){
                request.getSession().setAttribute(Utils.INFO_MSG, "Train Rides found");
            }
            else {
                request.getSession().setAttribute(Utils.INFO_MSG, "No one Train Ride matches required criterias");
            }
            response.sendRedirect(Pages.RIDES);
            return;


        } catch (IncorrectTimeFormatException | IncorrectTimeIntervalException e) {
            String errorMsg = "Error! " + e.getMessage();
            request.getSession().setAttribute(Utils.IS_VALIDATION_ERR, true);
            request.getSession().setAttribute(Utils.VALIDATION_ERROR_MSG, errorMsg);
            this.showEmptyRideList(request,response);
            return;
        }
        */
    }

    private void showEmptyRideList(HttpServletRequest request, HttpServletResponse response)  throws IOException {

        /*
        RouteService routeService = new RouteService();
        request.getSession().setAttribute(Utils.IS_SEARCH_RESULT,false);
        request.getSession().setAttribute(Utils.ROUTES, routeService.findAllRoutes());
        request.getSession().setAttribute(Utils.TRAIN_RIDES, new ArrayList<TrainRide>());
        response.sendRedirect(Pages.RIDES);
        */
    }

    private void processAllTrainRides(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        TrainService trainService = new TrainService();
        RouteService routeService = new RouteService();
        StationService stationService = new StationService();

        List<TrainRide> rides = trainService.findAllTrainRides();
        List<Station> stations = stationService.findAllStations();

        request.getSession().setAttribute(Utils.IS_SEARCH_RESULT,false);
        request.getSession().setAttribute(Utils.TRAIN_RIDES,rides);
        request.getSession().setAttribute(Utils.STATIONS,stations);
        request.getSession().setAttribute(Utils.ROUTES, routeService.findAllRoutes());
        request.getSession().setAttribute(Utils.HAS_CURRENT_TRAIN,false);
        */
        response.sendRedirect(Pages.RIDES);
    }



}
