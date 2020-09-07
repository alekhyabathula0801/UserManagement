package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = new UserDAO().getUserDetails(userName,password);
        UserManagementService service = new UserManagementService();
//        System.out.println(user);
        if(user != null) {
            user.setUserName(userName);
            session.setAttribute("user",user);
            List<Integer> dashboardPermissions = service.getPermissions(1,user.getUserId());
            List<Integer> settingsPermissions = service.getPermissions(2,user.getUserId());
            List<Integer> userInformationPermissions = service.getPermissions(3,user.getUserId());
            List<Integer> webpage1Permissions = service.getPermissions(4,user.getUserId());
            List<Integer> webpage2Permissions = service.getPermissions(5,user.getUserId());
            List<Integer> webpage3Permissions = service.getPermissions(6,user.getUserId());
            service.setUserLogin(user.getUserId());
            session.setAttribute("dashboardPermissions",dashboardPermissions);
            session.setAttribute("settingsPermissions",settingsPermissions);
            session.setAttribute("userInformationPermissions",userInformationPermissions);
            session.setAttribute("webpage1Permissions",webpage1Permissions);
            session.setAttribute("webpage2Permissions",webpage2Permissions);
            session.setAttribute("webpage3Permissions",webpage3Permissions);
            if(dashboardPermissions != null) {
                response.sendRedirect("Dashboard");
            } else {
                response.sendRedirect("webpage1");
            }
        } else {
            session.setAttribute("message",service.convertToString(Messages.USER_NAME_AND_PASSWORD_DOESNOT_MATCH));
            response.sendRedirect("login");
        }
    }

}
