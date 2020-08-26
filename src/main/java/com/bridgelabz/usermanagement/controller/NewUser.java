package com.bridgelabz.usermanagement.controller;

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

@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        com.bridgelabz.usermanagement.model.NewUser newUser = new com.bridgelabz.usermanagement.model.NewUser();
        newUser.setFirstName(request.getParameter("firstName"));
        newUser.setMiddleName(request.getParameter("middleName"));
        newUser.setLastName(request.getParameter("lastName"));
        newUser.setDateOfBirth(request.getParameter("dateOfBirth"));
        newUser.setGender(request.getParameter("gender"));
        newUser.setCountry(request.getParameter("country"));
        newUser.setCountryCode(request.getParameter("countryCode"));
        newUser.setMobileNumber(Long.valueOf(request.getParameter("mobileNumber")));
        newUser.setEmailId(request.getParameter("email"));
        newUser.setAddress(request.getParameter("address"));
        newUser.setUserName(request.getParameter("userName"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setUserRole(request.getParameter("userRole"));
        User user = (User) session.getAttribute("user");
        newUser.setCreatorUser(user.getUserName());
        UserManagementService service = new UserManagementService();
        Messages messages = service.addUser(newUser);
        session.setAttribute("message",service.convertToString(messages));
        response.sendRedirect("new_user");
    }
}
