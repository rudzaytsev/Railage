package com.tsystems.jschool.railage.view.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.jschool.railage.domain.Station;
import com.tsystems.jschool.railage.domain.TrainRide;
import com.tsystems.jschool.railage.service.RouteService;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.exceptions.TimeTableConflictException;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.controllers.helpers.AddRideFormParams;
import com.tsystems.jschool.railage.view.controllers.helpers.RouteHelper;
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
 * Created by rudolph on 29.07.15.
 */
@Controller
public class RidesController {

    @Autowired
    TrainService trainService;

    @Autowired
    RouteService routeService;

    @Autowired
    StationService stationService;

    @Autowired
    ControllersUtils controllersUtils;


    @RequestMapping(value = "/ajax/route", method = RequestMethod.POST)
    public void sendRouteInfoByAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        JSONParser jsonParser = new JSONParser();
        Integer routeId;
        try {
            JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
            routeId = Integer.parseInt(jsonObj.get("routeId").toString());
            ObjectMapper mapper = new ObjectMapper();
            RouteHelper routeHelper = RouteHelper.map(routeService.findRouteById(routeId));
            resp.setContentType("application/json");
            mapper.writeValue(resp.getOutputStream(), routeHelper);
        }
        catch(NumberFormatException | ParseException e ){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/add/ride", method = RequestMethod.POST)
    public String addRide(AddRideFormParams params, Model model){

        Integer routeId =  Integer.parseInt(params.getRouteId());
        String dateStr = params.getRideDate();

        try {
            trainService.addTrainRide(routeId, dateStr);
        }
        catch (java.text.ParseException | TimeTableConflictException e) {
            controllersUtils.addErrorMessage(model,e.getMessage());
            return Pages.RIDES;
        }
        finally {
            this.showAllRides(model);
            controllersUtils.addRideAdditionFormParams(model);
        }
        controllersUtils.addSuccessMessage(model,"Train ride added!");

        return Pages.RIDES;
    }

    private void showAllRides(Model model){

        List<TrainRide> rides = trainService.findAllTrainRides();
        List<Station> stations = stationService.findAllStations();

        model.addAttribute(Utils.IS_SEARCH_RESULT, false);
        model.addAttribute(Utils.TRAIN_RIDES, rides);
        model.addAttribute(Utils.STATIONS, stations);
        model.addAttribute(Utils.ROUTES, routeService.findAllRoutes());
        model.addAttribute(Utils.HAS_CURRENT_TRAIN, false);
    }
}
