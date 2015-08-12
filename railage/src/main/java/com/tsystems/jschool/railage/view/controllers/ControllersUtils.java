package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.domain.Station;
import com.tsystems.jschool.railage.service.RouteService;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.controllers.helpers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by rudolph on 28.07.15.
 */
@Service
public class ControllersUtils {

    @Autowired
    TrainService trainService;

    @Autowired
    StationService stationService;

    @Autowired
    RouteService routeService;

    public final void addRidesFormsData2Model(Model model){

        List<Station> stations = stationService.findAllStations();

        model.addAttribute(Utils.IS_SEARCH_RESULT, false);
        model.addAttribute(Utils.STATIONS, stations);
        model.addAttribute(Utils.ROUTES, routeService.findAllRoutes());
        model.addAttribute(Utils.HAS_CURRENT_TRAIN, false);
    }

    public final void addRides2Model(Model model){
        model.addAttribute(Utils.TRAIN_RIDES,trainService.findAllTrainRides());
    }

    public final void addTrains2Model(Model model){
        model.addAttribute(Utils.TRAINS,trainService.findAllTrains());
    }

    public final void addRoutes2Model(Model model){
        model.addAttribute(Utils.ROUTES,routeService.findAllRoutes());
    }

    public final void addStations2Model(Model model){
        model.addAttribute(Utils.STATIONS,stationService.findAllStations());
    }

    public final void addTrainAdditionFormParams(Model model){
        model.addAttribute(Utils.TRAIN_ADDITION_FORM_PARAMS, new AddTrainFormParams());
    }

    public final void addRideAdditionFormParams(Model model){
       model.addAttribute(Utils.RIDE_ADDITION_FORM_PARAMS, new AddRideFormParams());
    }

    public final void addRidesSearchFormParams(Model model){
       model.addAttribute(Utils.RIDE_SEARCH_FORM_PARAMS,new FindRidesFormParams());
    }

    public final void addBuyTicketFormParams(Model model){
        model.addAttribute(Utils.BUY_TICKET_FORM_PARAMS,new BuyTicketFormParams());
    }

    public final void addRidesFormGroup(Model model){
        this.addRideAdditionFormParams(model);
        this.addRidesSearchFormParams(model);
        this.addBuyTicketFormParams(model);
    }

    public final void addRoutesFormGroup(Model model){
        this.addRouteAdditionFormParams(model);
    }

    private void addRouteAdditionFormParams(Model model){
        model.addAttribute(Utils.ROUTE_ADDITION_FORM_PARAMS,new RouteFormParams());
    }


    public final void addErrorMessage(Model model,String errorMsg){
        model.addAttribute(Utils.IS_VALIDATION_ERR, true);
        model.addAttribute(Utils.VALIDATION_ERROR_MSG,errorMsg);
    }

    public final void addSuccessMessage(Model model, String message){
        model.addAttribute(Utils.SUCCESS, true);
        model.addAttribute(Utils.INFO_MSG, message);
    }
}
