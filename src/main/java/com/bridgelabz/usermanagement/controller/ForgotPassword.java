package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.service.EmailService;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
    final static Logger logger = Logger.getLogger(ForgotPassword.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("forgotPasswordEmail");
        logger.info("request for forgot password received with email id "+email);
        HttpSession session = request.getSession();
        UserManagementService service = new UserManagementService();
        if(new EmailService().sendMail(email)) {
            logger.info("recovery password sent to email : "+email);
            session.setAttribute("message",service.convertToString(Messages.RECOVERY_PASSWORD_SENT_TO_REGISTERED_EMAIL_LOGIN_TO_CONTINUE));
            response.sendRedirect("login");
        } else {
            logger.info("request email id : "+email+"doesn't exist");
            session.setAttribute("message",service.convertToString(Messages.EMAIL_ID_DOESNOT_EXISTS));
            response.sendRedirect("forgot_password");
        }
    }
}
