package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.Country;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManagementService service = new UserManagementService();
        Long numberOfUsers = service.getNumberOfUsers();
        Long numberOfActiveUsers = service.getNumberOfUsersByStatus("Active");
        Long numberOfInactiveUsers = service.getNumberOfUsersByStatus("Inactive");
        Long numberOfUsersOnline = service.getNumberOfUsersOnline();
        Double femaleRatio = service.getFemaleRatio();
        Double maleRatio = service.getMaleRatio();
        List<User> recentRegistrations = service.getRecentRegistrations(10);
        List<Country> countriesWithMaximumUsers = service.getCountriesWithMaximumUsers(3);
        List<Integer> age = service.getNumberOfUsersByAge();
        HttpSession session = request.getSession();
        session.setAttribute("numberOfUsers",numberOfUsers);
        session.setAttribute("numberOfActiveUsers",numberOfActiveUsers);
        session.setAttribute("numberOfInactiveUsers",numberOfInactiveUsers);
        session.setAttribute("numberOfUsersOnline",numberOfUsersOnline);
        session.setAttribute("recentRegistrations",recentRegistrations);
        session.setAttribute("femaleRatio",femaleRatio);
        session.setAttribute("maleRatio",maleRatio);
        session.setAttribute("countriesWithMaximumUsers",countriesWithMaximumUsers);
        session.setAttribute("age",age);
        response.sendRedirect("dashboard");
    }
}
