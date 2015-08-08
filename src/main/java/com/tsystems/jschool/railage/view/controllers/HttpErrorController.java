package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.view.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by rudolph on 08.08.15.
 */
@Controller
public class HttpErrorController {

    @RequestMapping(value="/error404", method = {RequestMethod.GET,RequestMethod.HEAD})
    public String error404(){
        return Pages.ERROR_404;
    }
}
