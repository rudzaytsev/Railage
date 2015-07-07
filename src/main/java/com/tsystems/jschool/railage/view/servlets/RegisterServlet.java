package com.tsystems.jschool.railage.view.servlets;

import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.UserService;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by rudolph on 26.06.15.
 */
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserService();
        TrainService trainService = new TrainService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        boolean validUserData = userService.isValidUserData(login,password,role);
        Integer id = userService.createUser(login,password,role);
        if (validUserData && id != null){

            HttpSession session = request.getSession();
            session.setAttribute(Utils.USER_ID_SESSION_ATTRIB,id);
            session.setAttribute(Utils.USER_LOGIN_SESSION_ATTRIB,login);
            session.setAttribute(Utils.LOGGED_SESSION_ATTRIB,true);
            session.setAttribute(Utils.IS_EMPLOYEE_SESSION_ATTRIB,Utils.isEmployee(role));
            session.setAttribute(Utils.TRAINS, trainService.findAllTrains());
            response.sendRedirect(Pages.TRAINS);
            return;
        }
        else {
            response.sendRedirect(Pages.REGISTRATION);
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
