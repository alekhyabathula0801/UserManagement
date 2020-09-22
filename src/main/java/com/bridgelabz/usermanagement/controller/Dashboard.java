package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.DashboardService;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

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
    final static Logger logger = Logger.getLogger(Dashboard.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("request for Dashboard received");
        UserManagementService service = new UserManagementService();
        DashboardService dashboardService = new DashboardService();
        Long numberOfUsers = service.getNumberOfUsers();
        Long numberOfActiveUsers = dashboardService.getNumberOfUsersByStatus("Active");
        Long numberOfInactiveUsers = dashboardService.getNumberOfUsersByStatus("Inactive");
        Long numberOfUsersOnline = dashboardService.getNumberOfUsersOnline();
        List<User> recentRegistrations = dashboardService.getRecentRegistrations(10);
        logger.info("number of users "+numberOfUsers+", number of active users "+numberOfActiveUsers+
                ", number of inactive users "+numberOfInactiveUsers+", numberOfUsersOnline "+numberOfUsersOnline);
        logger.info("recent registrations are "+recentRegistrations);
        HttpSession session = request.getSession();
        session.setAttribute("numberOfUsers",numberOfUsers);
        session.setAttribute("numberOfActiveUsers",numberOfActiveUsers);
        session.setAttribute("numberOfInactiveUsers",numberOfInactiveUsers);
        session.setAttribute("numberOfUsersOnline",numberOfUsersOnline);
        session.setAttribute("recentRegistrations",recentRegistrations);
        logger.info("response is redirected to dashboard page");
        response.sendRedirect("dashboard");
    }
}
