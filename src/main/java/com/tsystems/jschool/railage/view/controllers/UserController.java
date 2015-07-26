package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.domain.User;
import com.tsystems.jschool.railage.service.UserService;
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
public class UserController {

    //@Autowired
    //TrainService trainService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String welcome(){

        return Pages.INDEX;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String register(){

        return Pages.REGISTRATION;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpSession session,User user,Model model){

        User registeredUser = userService.findUser(
                                user.getLogin(),user.getPassword());

        boolean userIsLoggedIn = (registeredUser != null);
        if (!userIsLoggedIn){
            return Pages.INDEX;
        }

        boolean isEmployee = Utils.isEmployee(registeredUser.getRole());
        session.setAttribute(Utils.LOGGED_SESSION_ATTRIB, userIsLoggedIn);
        session.setAttribute(Utils.USER_LOGIN_SESSION_ATTRIB,registeredUser.getLogin());
        session.setAttribute(Utils.USER_ID_SESSION_ATTRIB,registeredUser.getId());
        session.setAttribute(Utils.IS_EMPLOYEE_SESSION_ATTRIB,isEmployee);

        return Pages.TRAINS;
    }

    @RequestMapping(value="/register/user", method = RequestMethod.POST)
    public String registerUser(HttpSession session,User user,Model model){

        boolean isValidUserData = userService.isValidUserData(user);
        if(!isValidUserData){
            return Pages.REGISTRATION;
        }
        Integer id = userService.createUser(user);
        if(id == null){
            return Pages.REGISTRATION;
        }
        session.setAttribute(Utils.USER_ID_SESSION_ATTRIB,id);
        session.setAttribute(Utils.USER_LOGIN_SESSION_ATTRIB,user.getLogin());
        session.setAttribute(Utils.LOGGED_SESSION_ATTRIB,true);
        session.setAttribute(Utils.IS_EMPLOYEE_SESSION_ATTRIB,Utils.isEmployee(user.getRole()));

        return Pages.TRAINS;

    }


}
