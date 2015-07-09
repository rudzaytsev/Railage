package com.tsystems.jschool.railage.view.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.jschool.railage.service.RouteService;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.view.servlets.helpers.RouteHelper;
import com.tsystems.jschool.railage.view.servlets.helpers.StationHelper;
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



        // 1. get received JSON data from request
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
            } else {
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
