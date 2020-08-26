package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = new UserDAO().getUserDetails(userName,password);
        if( user != null) {
            user.setUserName(userName);
            session.setAttribute("user",user);
            response.sendRedirect("dashboard");
        } else {
            session.setAttribute("message","Username and Password doesn't match");
            response.sendRedirect("login");
        }
    }

}
