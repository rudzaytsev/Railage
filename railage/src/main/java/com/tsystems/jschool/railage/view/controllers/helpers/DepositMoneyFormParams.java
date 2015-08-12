package com.tsystems.jschool.railage.view.controllers.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudolph on 09.08.15.
 */
public class DepositMoneyFormParams {

    private String selectedAmount;

    private String lastViewName;

    private List<String> amounts = new ArrayList<>();

    {
        amounts.add("500");
        amounts.add("1000");
        amounts.add("1500");
        amounts.add("2000");
        amounts.add("3000");
        amounts.add("4000");
        amounts.add("5000");
    }

    public String getSelectedAmount() {
        return selectedAmount;
    }

    public void setSelectedAmount(String selectedAmount) {
        this.selectedAmount = selectedAmount;
    }

    public List<String> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<String> amounts) {
        this.amounts = amounts;
    }

    public String getLastViewName() {
        return lastViewName;
    }

    public void setLastViewName(String lastViewName) {
        this.lastViewName = lastViewName;
    }
}
