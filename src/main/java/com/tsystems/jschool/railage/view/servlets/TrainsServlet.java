package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by rudolph on 28.06.15.
 */
public class TrainsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.addTrain(request,response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        Integer trainId = Utils.extractId(uri);
        if (trainId == null){
            processTrains(request,response);
            return;
        }
        else {
            processTrainRides(trainId,request,response);
            return;
        }
    }

    private void addTrain(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        String trainNumber = request.getParameter("trainNumber");
        try {
            Integer seats = Integer.parseInt(request.getParameter("seatsNumber"));
            TrainService trainService = new TrainService();
            trainService.addTrain(trainNumber,seats);
        }
        catch(NumberFormatException e){
            request.getSession().setAttribute(Utils.IS_VALIDATION_ERR, true);
            request.getSession().setAttribute(Utils.VALIDATION_ERROR_MSG,
                                            "Can Add Train. Seats is not a number!");
            processTrains(request, response);
            return;
        }
        catch (NotPositiveNumberOfSeatsException |
               IncorrectParameterException |
               DomainObjectAlreadyExistsException e) {

            String errorMsg = "Can not add train. " + e.getMessage();
            request.getSession().setAttribute(Utils.IS_VALIDATION_ERR, true);
            request.getSession().setAttribute(Utils.VALIDATION_ERROR_MSG,errorMsg);
            processTrains(request, response);
            return;
        }
        request.getSession().setAttribute(Utils.SUCCESS, true);
        request.getSession().setAttribute(Utils.INFO_MSG,"Train added");
        processTrains(request, response);
        return;
        */

    }


    private void processTrains(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TrainService trainService = new TrainService();
        HttpSession session = request.getSession();
        session.setAttribute(Utils.TRAINS,trainService.findAllTrains());
        response.sendRedirect(Pages.TRAINS);

    }

    private void processTrainRides(Integer trainId,HttpServletRequest request, HttpServletResponse response)
                throws IOException {

        /*
        TrainService trainService = new TrainService();
        StationService stationService = new StationService();

        HttpSession session = request.getSession();
        List<TrainRide> rides = trainService.findAllRidesByTrainId(trainId);
        List<Station> stations = stationService.findAllStations();
        request.getSession().setAttribute(Utils.IS_SEARCH_RESULT,false);
        session.setAttribute(Utils.TRAIN_RIDES, rides);
        session.setAttribute(Utils.STATIONS,stations);
        session.setAttribute(Utils.HAS_CURRENT_TRAIN,true);
        session.setAttribute(Utils.CURRENT_TRAIN, trainService.findTrainById(trainId));
        response.sendRedirect(Pages.RIDES);
        */
    }
}
