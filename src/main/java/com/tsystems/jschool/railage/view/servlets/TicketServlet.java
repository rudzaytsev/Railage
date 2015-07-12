package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.domain.Passenger;
import com.tsystems.jschool.railage.service.PassengerService;
import com.tsystems.jschool.railage.service.TicketService;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.exceptions.BookingTimeLimitIsOverException;
import com.tsystems.jschool.railage.service.exceptions.InvalidBoardingStationInRouteException;
import com.tsystems.jschool.railage.service.exceptions.NoFreeSeatsForRideException;
import com.tsystems.jschool.railage.service.exceptions.PassengerAlreadyBookedTicketOnRideException;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by rudolph on 12.07.15.
 */
public class TicketServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.buyTicket(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void buyTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String passengerName = request.getParameter("passengerName");
        String passengerLastName = request.getParameter("passengerLastName");
        String passengerBirthDateStr = request.getParameter("passengerBirthDate");
        Integer boardingStationId = Integer.parseInt(
                                        request.getParameter("boardingStationId"));

        Integer rideId = Integer.parseInt(request.getParameter("ride_id_for_ticket"));

        PassengerService passengerService = new PassengerService();
        TrainService trainService = new TrainService();
        TicketService ticketService = new TicketService();

        HttpSession session = request.getSession();

        try {
            Passenger passenger = passengerService.createPassenger(
                    passengerName,passengerLastName,passengerBirthDateStr);

            ticketService.buyTicket(rideId, boardingStationId, passenger);

            session.setAttribute(Utils.SUCCESS, true);
            session.setAttribute(Utils.INFO_MSG, "Ticket was bought!");

        }
        catch (ParseException | NoFreeSeatsForRideException |
               PassengerAlreadyBookedTicketOnRideException  |
               BookingTimeLimitIsOverException |
               InvalidBoardingStationInRouteException e) {

             session.setAttribute(Utils.IS_VALIDATION_ERR, true);
             session.setAttribute(Utils.VALIDATION_ERROR_MSG, e.getMessage());
        }

        List<Passenger> passengers = passengerService.findPassengersByRideId(rideId);
        session.setAttribute(Utils.HAS_CURRENT_TRAIN,false);
        session.setAttribute(Utils.HAS_CURRENT_RIDE,true);
        session.setAttribute(Utils.CURRENT_TRAIN_RIDE,trainService.findTrainRideById(rideId));
        session.setAttribute(Utils.PASSENGERS, passengers);
        response.sendRedirect(Pages.PASSENGERS);
        return;

    }
}
