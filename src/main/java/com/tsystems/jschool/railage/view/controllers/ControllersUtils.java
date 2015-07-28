package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.servlets.helpers.AddTrainFormParams;
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

    public final void addTrains2Model(Model model){
        model.addAttribute(Utils.TRAINS,trainService.findAllTrains());
    }

    public final void addTrainAdditionFormParams(Model model){
        model.addAttribute(Utils.TRAIN_ADDITION_FORM_PARAMS, new AddTrainFormParams());
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
