package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.service.UserManagementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManagementService service = new UserManagementService();
        Long numberOfUsers = service.getNumberOfUsers();
        Long numberOfActiveUsers = service.getNumberOfUsersByStatus("Active");
        HttpSession session = request.getSession();
        session.setAttribute("numberOfUsers",numberOfUsers);
        session.setAttribute("numberOfActiveUsers",numberOfActiveUsers);
        response.sendRedirect("dashboard");
    }
}
