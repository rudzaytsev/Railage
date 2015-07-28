package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectParameterException;
import com.tsystems.jschool.railage.service.exceptions.NotPositiveNumberOfSeatsException;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.servlets.helpers.AddTrainFormParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by rudolph on 28.07.15.
 */
@Controller
public class TrainsController {

    @Autowired
    TrainService trainService;

    @RequestMapping(value = "/add/train", method = RequestMethod.POST)
    public String addTrain(AddTrainFormParams params, Model model){

        String trainNumber = params.getTrainNumber();

        try {
            Integer seats = Integer.parseInt(params.getSeatsNumber());
            trainService.addTrain(trainNumber,seats);
        }
        catch(NumberFormatException e){

            model.addAttribute(Utils.IS_VALIDATION_ERR, true);
            model.addAttribute(Utils.VALIDATION_ERROR_MSG,
                    "Can Add Train. Seats is not a number!");

            this.addTrainsToModel(model);
            return Pages.TRAINS;
        }
        catch (NotPositiveNumberOfSeatsException |
               IncorrectParameterException |
               DomainObjectAlreadyExistsException e) {

            String errorMsg = "Can not add train. " + e.getMessage();
            model.addAttribute(Utils.IS_VALIDATION_ERR, true);
            model.addAttribute(Utils.VALIDATION_ERROR_MSG, errorMsg);
            this.addTrainsToModel(model);
            return Pages.TRAINS;
        }
        model.addAttribute(Utils.SUCCESS, true);
        model.addAttribute(Utils.INFO_MSG, "Train added");
        this.addTrainsToModel(model);
        return Pages.TRAINS;
    }

    private void addTrainsToModel(Model model){
        model.addAttribute(Utils.TRAINS,trainService.findAllTrains());
    }
}
