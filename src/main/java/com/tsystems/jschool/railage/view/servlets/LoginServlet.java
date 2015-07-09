package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.UserService;
import com.tsystems.jschool.railage.service.helper.Triple;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



/**
 * Created by rudolph on 25.06.15.
 */
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        Triple<Boolean,Integer,String> triple = userService.logInUser(login,password);
        boolean logged = triple.getFirst();
        TrainService trainService = new TrainService();

        if (logged){
            int id = triple.getSecond();
            boolean isEmployee = Utils.isEmployee(triple.getThird());
            HttpSession session = request.getSession();
            session.setAttribute(Utils.LOGGED_SESSION_ATTRIB, logged);
            session.setAttribute(Utils.USER_LOGIN_SESSION_ATTRIB,login);
            session.setAttribute(Utils.USER_ID_SESSION_ATTRIB,id);
            session.setAttribute(Utils.IS_EMPLOYEE_SESSION_ATTRIB,isEmployee);
            session.setAttribute(Utils.TRAINS, trainService.findAllTrains());
            response.sendRedirect(Pages.TRAINS);
            return;
        }
        else {
            response.sendRedirect(Pages.INDEX);
            return;
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(Pages.REGISTRATION);
        return;
    }
}
