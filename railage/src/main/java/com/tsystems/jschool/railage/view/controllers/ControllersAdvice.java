package com.tsystems.jschool.railage.view.controllers;

import com.tsystems.jschool.railage.view.Utils;
import com.tsystems.jschool.railage.view.controllers.helpers.DepositMoneyFormParams;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by rudolph on 09.08.15.
 */
@ControllerAdvice
public class ControllersAdvice {

    @ModelAttribute
    public void addGlobalAttributes(Model model){
        model.addAttribute(Utils.DEPOSIT_MONEY_FORM_PARAMS, new DepositMoneyFormParams());
    }

}
