package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserList")
public class UserList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> usersDetails = new UserManagementService().getAllUsers();
        request.setAttribute("usersDetails", usersDetails);
        RequestDispatcher rd = request.getRequestDispatcher("users");
        rd.forward(request, response);
    }
}
