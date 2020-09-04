package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user_list")
public class UserList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManagementService service = new UserManagementService();
        Long numberOfUsers = service.getNumberOfUsers();
        List<User> usersDetails = service.getLimitedUsers(1,10);
        request.setAttribute("active_page",1);
        request.setAttribute("numberOfUsersToDisplay",10);
        request.setAttribute("numberOfUsers",numberOfUsers);
        request.setAttribute("usersDetails", usersDetails);
        RequestDispatcher rd = request.getRequestDispatcher("users_list");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        int numberOfUsersToDisplay = Integer.parseInt(request.getParameter("number-of-users"));
        int pageId = Integer.parseInt(request.getParameter("active-page-id"));
        UserManagementService service = new UserManagementService();
        Long numberOfUsers = service.getNumberOfUsers();
        List<User> usersDetails = service.getLimitedUsers(pageId,numberOfUsersToDisplay);
        request.setAttribute("active_page",pageId);
        request.setAttribute("numberOfUsers",numberOfUsers);
        request.setAttribute("numberOfUsersToDisplay",numberOfUsersToDisplay);
        request.setAttribute("usersDetails", usersDetails);
        RequestDispatcher rd = request.getRequestDispatcher("users_list");
        rd.forward(request, response);
    }
}
