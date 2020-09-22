package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    final static Logger logger = Logger.getLogger(Logout.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("request for logout received");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        new UserManagementService().setUserLogout(user.getUserId());
        Enumeration<String> attributes = request.getSession().getAttributeNames();
        while (attributes.hasMoreElements()) {
            String attribute = attributes.nextElement();
            session.removeAttribute(attribute);
        }
        session.invalidate();
        request.getSession().setAttribute("message",new UserManagementService().convertToString(Messages.LOGOUT_SUCCESSFUL));
        response.sendRedirect("login");
    }
}
