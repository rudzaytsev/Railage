package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.domain.User;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.UserService;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.InvalidUserDataException;
import com.tsystems.jschool.railage.view.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

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


    @RequestMapping(value="/error403", method = RequestMethod.GET)
    public String error403(){
        return Pages.ERROR_403;
    }



}
