package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.service.UserManagementService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/Age")
public class Age extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userChoice = Integer.parseInt(request.getParameter("userChoice"));
        Map<String,Integer> age=null;
        UserManagementService service = new UserManagementService();
        switch (userChoice) {
            case 0:
                age = service.getNumberOfUsersByAge();
                break;
            case 1:
                age = service.getNumberOfUsersByAgeInCurrentYear();
                break;
            case 2:
                age = service.getNumberOfUsersByAgeCurrentMonth();
                break;
        }

        String json = new Gson().toJson(age);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
