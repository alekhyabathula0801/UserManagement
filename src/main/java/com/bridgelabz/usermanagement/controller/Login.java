package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Login")
public class Login extends HttpServlet {
    final static Logger logger = Logger.getLogger(Login.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("login request received");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserManagementService service = new UserManagementService();
        User user = service.getUserDetails(userName,password);
        if(user != null) {
            user.setUserName(userName);
            session.setAttribute("user",user);
            logger.info("user details are "+user);
            List<Integer> dashboardPermissions = service.getPermissions(1,user.getUserId());
            List<Integer> settingsPermissions = service.getPermissions(2,user.getUserId());
            List<Integer> userInformationPermissions = service.getPermissions(3,user.getUserId());
            List<Integer> webpage1Permissions = service.getPermissions(4,user.getUserId());
            List<Integer> webpage2Permissions = service.getPermissions(5,user.getUserId());
            List<Integer> webpage3Permissions = service.getPermissions(6,user.getUserId());
            service.setUserLogin(user.getUserId());
            logger.info("user permissions are " + dashboardPermissions + " " + settingsPermissions + " " +
                    userInformationPermissions + " " + webpage1Permissions + " " + webpage2Permissions + " "+
                    webpage3Permissions);
            session.setAttribute("dashboardPermissions",dashboardPermissions);
            session.setAttribute("settingsPermissions",settingsPermissions);
            session.setAttribute("userInformationPermissions",userInformationPermissions);
            session.setAttribute("webpage1Permissions",webpage1Permissions);
            session.setAttribute("webpage2Permissions",webpage2Permissions);
            session.setAttribute("webpage3Permissions",webpage3Permissions);
            if(dashboardPermissions != null) {
                logger.info("user is redirected to Dashbord");
                response.sendRedirect("Dashboard");
            } else {
                logger.info("user is redirected to Profile");
                response.sendRedirect("Profile");
            }
        } else {
            String message = service.getLoginMessage(userName);
            logger.info("user name is "+userName+" login failed with message -> " + message);
            session.setAttribute("message",message);
            response.sendRedirect("login");
        }
    }

}
