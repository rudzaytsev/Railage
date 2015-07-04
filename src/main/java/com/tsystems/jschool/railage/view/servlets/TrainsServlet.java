package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.domain.TrainRide;
import com.tsystems.jschool.railage.service.TrainService;
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
 * Created by rudolph on 28.06.15.
 */
public class TrainsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        Integer trainId = Utils.extractId(uri);
        if(trainId == null){
            processTrains(request,response);
            return;
        }
        else {
            processTrainRides(trainId,request,response);
            return;
        }
    }

    public void processTrains(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(Utils.TRAINS, TrainService.findAllTrains());
        response.sendRedirect(Pages.TRAINS);
    }

    public void processTrainRides(Integer trainId,HttpServletRequest request, HttpServletResponse response)
                throws IOException {
        HttpSession session = request.getSession();
        List<TrainRide> rides = TrainService.findAllRidesByTrainId(trainId);
        session.setAttribute(Utils.TRAIN_RIDES, rides);
        session.setAttribute(Utils.CURRENT_TRAIN, TrainService.findTrainById(trainId));
        response.sendRedirect(Pages.RIDES);
    }
}
