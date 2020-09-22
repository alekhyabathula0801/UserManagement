package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.service.UserManagementService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Gender")
public class Gender extends HttpServlet {
    final static Logger logger = Logger.getLogger(Gender.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userChoice = Integer.parseInt(request.getParameter("userChoice"));
        logger.info("request to get gender details with user choice "+userChoice+" received");
        UserManagementService service = new UserManagementService();
        List<Double> genderRatio = new ArrayList<>();
        Double femaleRatio = service.getFemaleRatio(userChoice);
        if(femaleRatio != null) {
            genderRatio.add(femaleRatio);
            genderRatio.add(service.getMaleRatio(userChoice));
        }
        logger.info("gender details are "+genderRatio);
        String json = new Gson().toJson(genderRatio);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
