package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.controllers.helpers.AddRideFormParams;
import com.tsystems.jschool.railage.view.controllers.helpers.AddTrainFormParams;
import com.tsystems.jschool.railage.view.controllers.helpers.FindRidesFormParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Created by rudolph on 28.07.15.
 */
@Service
public class ControllersUtils {

    @Autowired
    TrainService trainService;

    @Autowired
    StationService stationService;

    public final void addTrains2Model(Model model){
        model.addAttribute(Utils.TRAINS,trainService.findAllTrains());
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

    public final void addRidesFormGroup(Model model){
        this.addRideAdditionFormParams(model);
        this.addRidesSearchFormParams(model);
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
