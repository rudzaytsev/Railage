package com.tsystems.jschool.railage.view.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.jschool.railage.service.StationService;
import com.tsystems.jschool.railage.view.servlets.helpers.StationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by rudolph on 06.07.15.
 */
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StationService stationService = new StationService();

        // 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        // 3. Convert received JSON to Article
        List<StationHelper> stationHelpers = StationHelper.map(stationService.findAllStations());

        response.setContentType("application/json");

        // Send List<Article> as JSON to client
        mapper.writeValue(response.getOutputStream(), stationHelpers);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
