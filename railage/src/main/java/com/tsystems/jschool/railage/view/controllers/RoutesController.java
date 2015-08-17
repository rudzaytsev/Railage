package com.tsystems.jschool.railage.view.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.jschool.railage.domain.Route;
import com.tsystems.jschool.railage.domain.RoutePart;
import com.tsystems.jschool.railage.domain.enums.Period;
import com.tsystems.jschool.railage.service.RouteService;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.exceptions.DuplicatedStationsInRouteException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectStationsDepartureTimesOrderException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectTimeFormatException;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.controllers.helpers.AjaxRequestType;
import com.tsystems.jschool.railage.view.controllers.helpers.RouteFormParams;
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

    @Autowired
    StationService stationService;

    @Autowired
    ControllersUtils controllersUtils;

    private static org.apache.log4j.Logger logger =
            org.apache.log4j.Logger.getLogger(RoutesController.class);

    @RequestMapping(value = "/routes/all", method = RequestMethod.GET)
    public String showAllRoutes(Model model){

        controllersUtils.addRoutes2Model(model);
        logger.info("Show all routes");
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
            logger.info("Handled sendStationsInRoute Ajax request with body = " + json);
        }
        catch (ParseException | NumberFormatException e) {
            logger.error(e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void sendStationHelpersByRoute(HttpServletResponse response, Integer routeId) throws IOException {

        logger.info("Invoked sendStationHelpersByRoute()");
        ObjectMapper mapper = new ObjectMapper();

        Route route = routeService.findRouteById(routeId);

        List<RoutePart> routeParts = route.getRouteParts();
        List<StationHelper> helpers = StationHelper.mapRouteParts(routeParts);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), helpers);
    }

    @RequestMapping(value = "/build/route", method = RequestMethod.GET)
    public String showRouteBuilder(Model model){

        controllersUtils.addStations2Model(model);
        controllersUtils.addTrains2Model(model);
        model.addAttribute(Utils.PERIODS, Period.getPeriodsAsList());
        controllersUtils.addRoutesFormGroup(model);
        logger.info("Show routeBuilder page");

        return Pages.ROUTE_BULDER;
    }

    @RequestMapping(value = "/add/route", method = RequestMethod.POST)
    public String addRoute(HttpServletRequest request,Model model){

        RouteFormParams params = new RouteFormParams();
        params.fill(request);
        try {
            routeService.validate(params);
            routeService.addRoute(params);
            controllersUtils.addSuccessMessage(model,"Route Added!");
            logger.info("Added route with params = " + params);

        } catch (IncorrectStationsDepartureTimesOrderException |
                IncorrectTimeFormatException |
                DuplicatedStationsInRouteException e) {

           logger.error(e.getMessage());
           controllersUtils.addErrorMessage(model,e.getMessage());
        }
        controllersUtils.addRoutes2Model(model);

        return Pages.ROUTES;
    }



    @RequestMapping(value = "/ajax/stations", method = RequestMethod.POST)
    public void sendAjaxStations(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
            String requestName = (String) jsonObj.get("request");
            if(AjaxRequestType.STATIONS.value().equals(requestName)) {
                this.sendStationHelpers(resp);
                logger.info("Send Ajax StationHelpers for ajax request with body = " + json);
            }
        }
        catch (ParseException | NumberFormatException e) {
            logger.error(e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void sendStationHelpers(HttpServletResponse response) throws IOException {
        logger.info("Invoked sendStationHelpers()");
        ObjectMapper mapper = new ObjectMapper();
        List<StationHelper> stationHelpers = StationHelper.map(
                stationService.findAllStations());
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), stationHelpers);
    }

}
