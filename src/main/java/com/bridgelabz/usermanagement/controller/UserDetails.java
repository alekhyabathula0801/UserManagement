package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.model.Permissions;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UserDetails")
public class UserDetails extends HttpServlet {
    final static Logger logger = Logger.getLogger(UserDetails.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("request to get user details received");
        setUserDetails(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("request to get user details received");
        setUserDetails(request,response);
    }

    private void setUserDetails (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        UserManagementService service = new UserManagementService();
        User updateUser = service.getDetailsOfUser(userId);
        logger.info("user details are "+updateUser);
        Permissions permissions = service.getAllPermissions(userId);
        logger.info("user permissions are "+permissions);
        request.getSession().setAttribute("updateUserImage",updateUser.getUserImage());
        request.setAttribute("updateUser",updateUser);
        request.setAttribute("permissions",permissions);
        logger.info("request is dispatched to update_user page");
        RequestDispatcher rd = request.getRequestDispatcher("update_user");
        rd.forward(request, response);
    }
}
