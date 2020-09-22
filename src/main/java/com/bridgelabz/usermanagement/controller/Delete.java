package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
    final static Logger logger = Logger.getLogger(Delete.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        logger.info("request for delete user is received with id "+userId);
        new UserManagementService().deleteUser(userId);
        request.getSession().setAttribute("message","user with id = " + userId+ "deleted");
        response.sendRedirect("user_list");
    }
}
