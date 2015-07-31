package com.tsystems.jschool.railage.view.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rudolph on 13.07.15.
 */
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.logOut(request,response);
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*
        request.getSession().invalidate();
        response.sendRedirect(Pages.INDEX);
        */
    }
}
