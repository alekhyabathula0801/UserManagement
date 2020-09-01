package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.service.UserManagementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import java.io.IOException;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        new UserManagementService().deleteUser(userId);
        request.getSession().setAttribute("message","user with id = " + userId+ "deleted");
        response.sendRedirect("user_list");
    }
}
