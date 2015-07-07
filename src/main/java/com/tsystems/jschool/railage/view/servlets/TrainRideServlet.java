package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.domain.Passenger;
import com.tsystems.jschool.railage.service.PassengerService;
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        Integer trainRideId = Utils.extractId(uri);
        PassengerService passengerService = new PassengerService();
        if (trainRideId == null){
            String queryStr = request.getQueryString();
            Integer trainId = Utils.extractNumberParam(queryStr,"trainId");
            if (trainId != null){
               HttpSession session = request.getSession();
               List<Passenger> passengers = passengerService.findPassengersByTrainId(trainId);
               session.setAttribute(Utils.PASSENGERS, passengers);
               response.sendRedirect(Pages.PASSENGERS);
               return;
            }
            response.sendRedirect(Pages.ERROR);
            return;
        }
        else {
            HttpSession session = request.getSession();
            List<Passenger> passengers = passengerService.findPassengersByRideId(trainRideId);
            session.setAttribute(Utils.PASSENGERS, passengers);
            response.sendRedirect(Pages.PASSENGERS);
            return;
        }


    }



}
