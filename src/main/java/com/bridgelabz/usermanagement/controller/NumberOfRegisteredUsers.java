package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.service.DashboardService;
import com.bridgelabz.usermanagement.service.UserManagementService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/NumberOfRegisteredUsers")
public class NumberOfRegisteredUsers extends HttpServlet {
    final static Logger logger = Logger.getLogger(NumberOfRegisteredUsers.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userChoice = Integer.parseInt(request.getParameter("userChoice"));
        logger.info("request for number of registered users received with user choice :"+userChoice);
        Map<String,Long> numberOfRegisteredUsers=null;
        DashboardService service = new DashboardService();
        switch (userChoice) {
            case 0:
                numberOfRegisteredUsers = service.getAllTimeRegisteredUsers();
                break;
            case 1:
                numberOfRegisteredUsers = service.getRegisteredUsersInCurrentYear();
                break;
            case 2:
                numberOfRegisteredUsers = service.getAllTimeRegisteredUsersInCurrentMonth();
                break;
        }
        logger.info("number of registerd users are "+numberOfRegisteredUsers);
        String json = new Gson().toJson(numberOfRegisteredUsers);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
