package com.tsystems.jschool.railage.view.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.jschool.railage.domain.Route;
import com.tsystems.jschool.railage.domain.RoutePart;
import com.tsystems.jschool.railage.service.RouteService;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.controllers.helpers.StationHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    @RequestMapping(value = "/ajax/stationsbyroute", method = RequestMethod.POST)
    public void sendStationsInRoute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
            Integer routeId = Integer.parseInt(jsonObj.get("routeId").toString());
            this.sendStationHelpersByRoute(resp,routeId);
        }
        catch (ParseException | NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void sendStationHelpersByRoute(HttpServletResponse response, Integer routeId) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        Route route = routeService.findRouteById(routeId);

        List<RoutePart> routeParts = route.getRouteParts();
        List<StationHelper> helpers = StationHelper.mapRouteParts(routeParts);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), helpers);
    }
}
