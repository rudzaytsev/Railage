package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.domain.User;
import com.tsystems.jschool.railage.domain.enums.Period;
import com.tsystems.jschool.railage.security.UserAdapter;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.UserService;
import com.tsystems.jschool.railage.service.exceptions.*;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.controllers.helpers.DepositMoneyFormParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rudolph on 25.07.15.
 */
@Controller
public class UsersController {

    @Autowired
    TrainService trainService;

    @Autowired
    UserService userService;

    @Autowired
    ControllersUtils controllersUtils;


    @RequestMapping(value = {"/"},method = RequestMethod.GET)
    public String welcome(){

        return Pages.INDEX;
    }

    @RequestMapping(value = "/auth/error", method = RequestMethod.GET)
    public String showError(Model model){
        controllersUtils.addErrorMessage(model,"Invalid user login or password");

        return Pages.INDEX;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String register(){

        return Pages.REGISTRATION;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpSession session, Model model){

        controllersUtils.addTrains2Model(model);
        controllersUtils.addTrainAdditionFormParams(model);

        return Pages.TRAINS;
    }

    @RequestMapping(value="/register/user", method = RequestMethod.POST)
    public String registerUser(HttpSession session,User user,Model model){

        try {
            userService.isValidUserData(user);
            userService.createUser(user);
        }
        catch (InvalidUserDataException |
              DomainObjectAlreadyExistsException e){

            controllersUtils.addErrorMessage(model, e.getMessage());
            return Pages.REGISTRATION;
        }
        boolean authenticated = this.authRegisteredUser(user);
        if(!authenticated){
            controllersUtils.addErrorMessage(model, "User not authenticated!");
            return Pages.REGISTRATION;
        }
        controllersUtils.addTrains2Model(model);
        controllersUtils.addTrainAdditionFormParams(model);
        return Pages.TRAINS;
    }

    private boolean authRegisteredUser(User user){
        return userService.authRegisteredUser(user);
    }

    @RequestMapping(value = "/deposit/money", method = RequestMethod.POST)
    public String depositMoney(DepositMoneyFormParams params,Model model){

        String lastViewName = params.getLastViewName();
        Integer moneyAmount = Integer.parseInt(params.getSelectedAmount());
        UserAdapter userAdapter = userService.getPrincipal();
        try {
            userService.depositMoney(userAdapter.getLogin(), moneyAmount);
            controllersUtils.addSuccessMessage(model, "Balance increased!");
        }
        catch (UserNotFoundException | InvalidUserRoleException |
               OverflowWhileAdditionException e ) {
            controllersUtils.addErrorMessage(model,e.getMessage());
        }


        return this.selectView(lastViewName,model);
    }



    private String selectView(String lastViewName,Model model){
        List<String> contextDependPages = Arrays.asList(
                Pages.RIDES,Pages.PASSENGERS,Pages.TIMETABLE);

        if(lastViewName == null || contextDependPages.contains(lastViewName)){
            controllersUtils.addRides2Model(model);
            controllersUtils.addRidesFormsData2Model(model);
            controllersUtils.addRidesFormGroup(model);
            return Pages.RIDES;
        }
        else if(lastViewName.equals(Pages.STATIONS)){
            controllersUtils.addStations2Model(model);
            return Pages.STATIONS;
        }
        else if(lastViewName.equals(Pages.ROUTE_BULDER)){
            controllersUtils.addStations2Model(model);
            controllersUtils.addTrains2Model(model);
            model.addAttribute(Utils.PERIODS, Period.getPeriodsAsList());
            controllersUtils.addRoutesFormGroup(model);
            return Pages.ROUTE_BULDER;
        }
        else if(lastViewName.equals(Pages.TRAINS)){
            controllersUtils.addTrains2Model(model);
            controllersUtils.addTrainAdditionFormParams(model);
            return Pages.TRAINS;
        }
        else if(lastViewName.equals(Pages.ROUTES)){
            controllersUtils.addRoutes2Model(model);
            return Pages.ROUTES;
        }
        return lastViewName;
    }


    @RequestMapping(value="/error403", method = RequestMethod.GET)
    public String error403(){
        return Pages.ERROR_403;
    }



}
