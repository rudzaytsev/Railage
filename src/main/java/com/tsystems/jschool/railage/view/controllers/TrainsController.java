package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.domain.Route;
import com.tsystems.jschool.railage.domain.Station;
import com.tsystems.jschool.railage.domain.TrainRide;
import com.tsystems.jschool.railage.service.RouteService;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.IncorrectParameterException;
import com.tsystems.jschool.railage.service.exceptions.NotPositiveNumberOfSeatsException;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.controllers.helpers.AddTrainFormParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by rudolph on 28.07.15.
 */
@Controller
public class TrainsController {

    @Autowired
    TrainService trainService;

    @Autowired
    StationService stationService;

    @Autowired
    RouteService routeService;

    @Autowired
    ControllersUtils controllersUtils;

    @RequestMapping(value="/trains/{trainId}")
    public String showRides(@PathVariable("trainId") Integer trainId, Model model){

        List<TrainRide> rides = trainService.findAllRidesByTrainId(trainId);
        List<Station> stations = stationService.findAllStations();
        List<Route> routes = routeService.findAllRoutes();
        model.addAttribute(Utils.IS_SEARCH_RESULT, false);
        model.addAttribute(Utils.TRAIN_RIDES, rides);
        model.addAttribute(Utils.STATIONS, stations);
        model.addAttribute(Utils.ROUTES,routes);
        model.addAttribute(Utils.HAS_CURRENT_TRAIN, true);
        model.addAttribute(Utils.CURRENT_TRAIN, trainService.findTrainById(trainId));
        controllersUtils.addRideAdditionFormParams(model);

        return Pages.RIDES;
    }

    @RequestMapping(value = "/add/train", method = RequestMethod.POST)
    public String addTrain(AddTrainFormParams params, Model model){

        String trainNumber = params.getTrainNumber();

        try {
            Integer seats = Integer.parseInt(params.getSeatsNumber());
            trainService.addTrain(trainNumber,seats);
        }
        catch(NumberFormatException e){

            controllersUtils.addErrorMessage(model,
                    "Can Add Train. Seats is not a number!");
            return Pages.TRAINS;
        }
        catch (NotPositiveNumberOfSeatsException |
               IncorrectParameterException |
               DomainObjectAlreadyExistsException e) {

            String errorMsg = "Can not add train. " + e.getMessage();
            controllersUtils.addErrorMessage(model,errorMsg);
            return Pages.TRAINS;
        }
        finally {
            controllersUtils.addTrains2Model(model);
            controllersUtils.addTrainAdditionFormParams(model);
        }
        controllersUtils.addSuccessMessage(model,"Train added");

        return Pages.TRAINS;
    }


}
