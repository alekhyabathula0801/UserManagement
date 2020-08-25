package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("forgotPasswordEmail");
        HttpSession session = request.getSession();
        if(new EmailService().sendMail(email)) {
            session.setAttribute("message","Recovery password sent to registered Email. Login to continue");
            response.sendRedirect("login");
        } else {
            session.setAttribute("message","Entered Email doesn't exists");
            response.sendRedirect("forgot_password");
        }
    }
}
