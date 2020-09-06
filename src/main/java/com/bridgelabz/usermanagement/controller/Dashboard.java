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
import java.util.List;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManagementService service = new UserManagementService();
        Long numberOfUsers = service.getNumberOfUsers();
        Long numberOfActiveUsers = service.getNumberOfUsersByStatus("Active");
        Long numberOfInactiveUsers = service.getNumberOfUsersByStatus("Inactive");
        double femaleRatio = service.getFemaleRatio();
        double maleRatio = service.getMaleRatio();
        List<User> recentRegistrations = service.getRecentRegistrations(10);
        List<Country> countriesWithMaximumUsers = service.getCountriesWithMaximumUsers(3);
        HttpSession session = request.getSession();
        session.setAttribute("numberOfUsers",numberOfUsers);
        session.setAttribute("numberOfActiveUsers",numberOfActiveUsers);
        session.setAttribute("numberOfInactiveUsers",numberOfInactiveUsers);
        session.setAttribute("recentRegistrations",recentRegistrations);
        session.setAttribute("femaleRatio",femaleRatio);
        session.setAttribute("maleRatio",maleRatio);
        session.setAttribute("countriesWithMaximumUsers",countriesWithMaximumUsers);
        response.sendRedirect("dashboard");
    }
}
