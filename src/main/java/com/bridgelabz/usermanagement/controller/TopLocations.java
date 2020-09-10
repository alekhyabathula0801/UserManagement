package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.Country;
import com.bridgelabz.usermanagement.service.UserManagementService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/TopLocations")
public class TopLocations extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userChoice = Integer.parseInt(request.getParameter("userChoice"));
        int numberOfUsers = Integer.parseInt(request.getParameter("numberOfUsers"));
        UserManagementService service = new UserManagementService();
        List<Country> countriesWithMaximumUsers = service.getCountriesWithMaximumUsers(numberOfUsers,userChoice);
        String json = new Gson().toJson(countriesWithMaximumUsers);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
