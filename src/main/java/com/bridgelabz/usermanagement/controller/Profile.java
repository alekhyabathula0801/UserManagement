package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
    final static Logger logger = Logger.getLogger(Profile.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("request to get profile details received");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserManagementService service = new UserManagementService();
        User userDetails = service.getDetailsOfUser(user.getUserId());
        logger.info("user details are "+userDetails);
        session.setAttribute("userDetails",userDetails);
        logger.info("response is redirected to profile page");
        response.sendRedirect("profile");
    }
}
