package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.domain.Route;
import com.tsystems.jschool.railage.service.RouteService;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by rudolph on 31.07.15.
 */
@Controller
public class RoutesController {

    @Autowired
    RouteService routeService;

    @RequestMapping(value = "/routes/all", method = RequestMethod.GET)
    public String showAllRoutes(Model model){

        List<Route> routes = routeService.findAllRoutes();
        model.addAttribute(Utils.ROUTES, routes);

        return Pages.ROUTES;
    }
}
