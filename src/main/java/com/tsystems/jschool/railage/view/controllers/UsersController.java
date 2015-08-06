package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.domain.User;
import com.tsystems.jschool.railage.service.TrainService;
import com.tsystems.jschool.railage.service.UserService;
import com.tsystems.jschool.railage.service.exceptions.DomainObjectAlreadyExistsException;
import com.tsystems.jschool.railage.service.exceptions.InvalidUserDataException;
import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;
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
    public String loginStub(HttpSession session, Model model){
        session.setAttribute(Utils.LOGGED_SESSION_ATTRIB, true);
        session.setAttribute(Utils.USER_LOGIN_SESSION_ATTRIB,"admin");
        session.setAttribute(Utils.USER_ID_SESSION_ATTRIB,10);
        session.setAttribute(Utils.IS_EMPLOYEE_SESSION_ATTRIB,true);

        controllersUtils.addTrains2Model(model);
        controllersUtils.addTrainAdditionFormParams(model);

        return Pages.TRAINS;
    }

    @RequestMapping(value = "/login1",method = RequestMethod.POST)
    public String login(HttpSession session, User user, Model model){
        User registeredUser = userService.findUser(
                                user.getLogin(), user.getPassword());

        boolean userIsLoggedIn = (registeredUser != null);
        if (!userIsLoggedIn){
           controllersUtils.addErrorMessage(model,"Invalid user login or password");
           return Pages.INDEX;
        }

        boolean isEmployee = Utils.isEmployee(registeredUser.getRole());
        session.setAttribute(Utils.LOGGED_SESSION_ATTRIB, userIsLoggedIn);
        session.setAttribute(Utils.USER_LOGIN_SESSION_ATTRIB,registeredUser.getLogin());
        session.setAttribute(Utils.USER_ID_SESSION_ATTRIB,registeredUser.getId());
        session.setAttribute(Utils.IS_EMPLOYEE_SESSION_ATTRIB,isEmployee);

        controllersUtils.addTrains2Model(model);
        controllersUtils.addTrainAdditionFormParams(model);

        return Pages.TRAINS;
    }

    @RequestMapping(value="/register/user", method = RequestMethod.POST)
    public String registerUser(HttpSession session,User user,Model model){

        try {
            userService.isValidUserData(user);
            Integer id = userService.createUser(user);
            session.setAttribute(Utils.USER_ID_SESSION_ATTRIB, id);
            session.setAttribute(Utils.USER_LOGIN_SESSION_ATTRIB, user.getLogin());
            session.setAttribute(Utils.LOGGED_SESSION_ATTRIB, true);
            session.setAttribute(Utils.IS_EMPLOYEE_SESSION_ATTRIB,
                                                    Utils.isEmployee(user.getRole()));
        }
        catch (InvalidUserDataException |
              DomainObjectAlreadyExistsException e){

            controllersUtils.addErrorMessage(model, e.getMessage());
            return Pages.REGISTRATION;
        }

        controllersUtils.addTrains2Model(model);
        controllersUtils.addTrainAdditionFormParams(model);

        return Pages.TRAINS;
    }


    @RequestMapping(value="/error403", method = RequestMethod.GET)
    public String error403(){
        return Pages.ERROR_403;
    }



}
