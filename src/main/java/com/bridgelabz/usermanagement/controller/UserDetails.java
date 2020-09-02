package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.model.Permissions;
import com.bridgelabz.usermanagement.service.UserManagementService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UserDetails")
public class UserDetails extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        UserManagementService service = new UserManagementService();
        User updateUser = service.getAllDetailsOfUser(userId);
        Permissions permissions = service.getAllPermissions(userId);
        request.setAttribute("updateUser",updateUser);
        request.setAttribute("permissions",permissions);
        RequestDispatcher rd = request.getRequestDispatcher("update_user");
        rd.forward(request, response);
    }
}
