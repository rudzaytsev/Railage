package com.tsystems.jschool.railage.view.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rudolph on 02.07.15.
 */
public class RoutesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        HttpSession session = request.getSession();
        RouteService routeService = new RouteService();
        List<Route> routes = routeService.findAllRoutes();
        session.setAttribute(Utils.ROUTES, routes);
        response.sendRedirect(Pages.ROUTES);
        */
        return;
    }
}
