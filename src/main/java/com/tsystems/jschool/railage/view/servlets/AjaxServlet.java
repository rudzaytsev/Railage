package com.tsystems.jschool.railage.view.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.jschool.railage.domain.Route;
import com.tsystems.jschool.railage.domain.RoutePart;
import com.tsystems.jschool.railage.domain.TrainRide;
import com.tsystems.jschool.railage.service.RouteService;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.view.controllers.helpers.RouteHelper;
import com.tsystems.jschool.railage.view.controllers.helpers.StationHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by rudolph on 06.07.15.
 */
public class AjaxServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
            String requestName = (String) jsonObj.get("request");
            if (AjaxRequestType.ROUTE.value().equals(requestName)) {
                Integer routeId = Integer.parseInt(jsonObj.get("routeId").toString());

                this.sendRouteHelper(response, routeId);
            }
            else if (AjaxRequestType.STATIONS_BY_RIDE.value().equals(requestName)) {
                Integer rideId = Integer.parseInt(jsonObj.get("rideId").toString());
                this.sendStationHelpersByRide(response, rideId);
            }
            else if (AjaxRequestType.STATIONS_BY_ROUTE.value().equals(requestName)) {
                Integer routeId = Integer.parseInt(jsonObj.get("routeId").toString());
                this.sendStationHelpersByRoute(response,routeId);
            }
            else {
                this.sendStationHelpers(response);
            }
        }
         catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        catch(NumberFormatException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private void sendStationHelpersByRoute(HttpServletResponse response, Integer routeId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        RouteService routeService = new RouteService();
        Route route = routeService.findRouteById(routeId);

        List<RoutePart> routeParts = route.getRouteParts();
        List<StationHelper> helpers = StationHelper.mapRouteParts(routeParts);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), helpers);
    }

    private void sendStationHelpersByRide(HttpServletResponse response, Integer rideId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TrainService trainService = new TrainService();
        TrainRide ride = trainService.findTrainRideById(rideId);
        RouteService routeService = new RouteService();
        Route route = routeService.findRouteById(ride.getRoute().getId());
       // List<RoutePart> routeParts = ride.getRoute().getRouteParts();

        List<RoutePart> routeParts = route.getRouteParts();
        List<StationHelper> helpers = StationHelper.mapRouteParts(routeParts);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), helpers);
    }

    private void sendRouteHelper(HttpServletResponse response, Integer routeId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RouteService routeService = new RouteService();
        RouteHelper routeHelper = RouteHelper.map(routeService.findRouteById(routeId));
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), routeHelper);

    }

    private void sendStationHelpers(HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StationService stationService = new StationService();
        List<StationHelper> stationHelpers = StationHelper.map(
                                                stationService.findAllStations());
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), stationHelpers);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
