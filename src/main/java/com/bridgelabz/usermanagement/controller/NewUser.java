package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.Permissions;
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
        if(messages.equals(Messages.USER_ADDED)) {
            Permissions permissions = new Permissions();
            permissions.setDashboardAdd(Integer.parseInt(request.getParameter("dashboard-add")));
            permissions.setDashboardDelete(Integer.parseInt(request.getParameter("dashboard-delete")));
            permissions.setDashboardModify(Integer.parseInt(request.getParameter("dashboard-modify")));
            permissions.setDashboardRead(Integer.parseInt(request.getParameter("dashboard-read")));
            permissions.setSettingsAdd(Integer.parseInt(request.getParameter("settings-add")));
            permissions.setSettingsDelete(Integer.parseInt(request.getParameter("settings-delete")));
            permissions.setSettingsModify(Integer.parseInt(request.getParameter("settings-modify")));
            permissions.setSettingsRead(Integer.parseInt(request.getParameter("settings-read")));
            permissions.setUserInformationAdd(Integer.parseInt(request.getParameter("userinfo-add")));
            permissions.setUserInformationDelete(Integer.parseInt(request.getParameter("userinfo-delete")));
            permissions.setUserInformationModify(Integer.parseInt(request.getParameter("userinfo-modify")));
            permissions.setUserInformationRead(Integer.parseInt(request.getParameter("userinfo-read")));
            permissions.setWebpage1Add(Integer.parseInt(request.getParameter("webpage1-add")));
            permissions.setWebpage1Delete(Integer.parseInt(request.getParameter("webpage1-delete")));
            permissions.setWebpage1Modify(Integer.parseInt(request.getParameter("webpage1-modify")));
            permissions.setWebpage1Read(Integer.parseInt(request.getParameter("webpage1-read")));
            permissions.setWebpage2Add(Integer.parseInt(request.getParameter("webpage2-add")));
            permissions.setWebpage2Delete(Integer.parseInt(request.getParameter("webpage2-delete")));
            permissions.setWebpage2Modify(Integer.parseInt(request.getParameter("webpage2-modify")));
            permissions.setWebpage2Read(Integer.parseInt(request.getParameter("webpage2-read")));
            permissions.setWebpage3Add(Integer.parseInt(request.getParameter("webpage3-add")));
            permissions.setWebpage3Delete(Integer.parseInt(request.getParameter("webpage3-delete")));
            permissions.setWebpage3Modify(Integer.parseInt(request.getParameter("webpage3-modify")));
            permissions.setWebpage3Read(Integer.parseInt(request.getParameter("webpage3-read")));
            service.addPermissions(permissions, newUser.getUserName(),newUser.getCreatorUser());
        }
        session.setAttribute("message",service.convertToString(messages));
        session.setAttribute("newUser",newUser);
        response.sendRedirect("new_user");
    }
}
