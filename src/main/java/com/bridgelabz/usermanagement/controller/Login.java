package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/Login")
public class Login extends HttpServlet {
    final static Logger logger = Logger.getLogger(Login.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("login request received");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("loginRememberMe");
        HttpSession session = request.getSession();
        UserManagementService service = new UserManagementService();
        User user = service.getUserDetails(userName,password);
        if(user != null) {
            user.setUserName(userName);
            if(rememberMe != null){
                Cookie cookie=new Cookie("userName",user.getUserName());
                cookie.setMaxAge(15*24*60*60);
                response.addCookie(cookie);
            }
            session.setAttribute("user",user);
            logger.debug("user details are "+user);
            service.setUserLogin(user.getUserId());
            getPermissionsAndRedirectToValidPage(response,session,service,user.getUserId());
        } else {
            String message = service.getLoginMessage(userName);
            logger.warn("user name is "+userName+" login failed with message -> " + message);
            session.setAttribute("message",message);
            response.sendRedirect("login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("request for remember me received");
        Cookie[] cookies = request.getCookies();
        boolean status = false;
        if (cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("userName")) {
                    status = true;
                    UserManagementService service = new UserManagementService();
                    String userName = cookie.getValue();
                    User user = service.getUserDetails(userName);
                    if(user != null) {
                        user.setUserName(userName);
                        logger.debug("user details are "+user);
                        HttpSession session = request.getSession();
                        session.setAttribute("user",user);
                        getPermissionsAndRedirectToValidPage(response,session,service,user.getUserId());
                    } else {
                        logger.info("user is redirected to login page");
                        response.sendRedirect("login");
                    }
                }
            }
            if(!status) {
                logger.info("user details are not saved, user is redirected to login page");
                response.sendRedirect("login");
            }
        } else {
            logger.info("user details are not saved, user is redirected to login page");
            response.sendRedirect("login");
        }
    }

    public void getPermissionsAndRedirectToValidPage(HttpServletResponse response, HttpSession session,
                                                     UserManagementService service, Long userId) throws ServletException,
            IOException {
        List<Integer> dashboardPermissions = service.getPermissions(1,userId);
        List<Integer> settingsPermissions = service.getPermissions(2,userId);
        List<Integer> userInformationPermissions = service.getPermissions(3,userId);
        List<Integer> webpage1Permissions = service.getPermissions(4,userId);
        List<Integer> webpage2Permissions = service.getPermissions(5,userId);
        List<Integer> webpage3Permissions = service.getPermissions(6,userId);
        logger.debug("user permissions are " + dashboardPermissions + " " + settingsPermissions + " " +
                userInformationPermissions + " " + webpage1Permissions + " " + webpage2Permissions + " "+
                webpage3Permissions);
        session.setAttribute("dashboardPermissions",dashboardPermissions);
        session.setAttribute("settingsPermissions",settingsPermissions);
        session.setAttribute("userInformationPermissions",userInformationPermissions);
        session.setAttribute("webpage1Permissions",webpage1Permissions);
        session.setAttribute("webpage2Permissions",webpage2Permissions);
        session.setAttribute("webpage3Permissions",webpage3Permissions);
        if(dashboardPermissions != null) {
            logger.info("user is redirected to Dashboard");
            response.sendRedirect("Dashboard");
        } else {
            logger.info("user is redirected to Profile");
            response.sendRedirect("Profile");
        }
    }

}
