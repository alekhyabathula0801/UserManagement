package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.Permissions;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Update")
public class Update extends HttpServlet {
    final static Logger logger = Logger.getLogger(Update.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("request for update user received");
        HttpSession session = request.getSession();
        User updateUser = new User();
        updateUser.setFirstName(request.getParameter("firstName"));
        updateUser.setMiddleName(request.getParameter("middleName"));
        updateUser.setLastName(request.getParameter("lastName"));
        updateUser.setDateOfBirth(request.getParameter("dateOfBirth"));
        updateUser.setGender(request.getParameter("gender"));
        updateUser.setCountry(request.getParameter("country"));
        updateUser.setCountryCode(request.getParameter("countryCode"));
        updateUser.setMobileNumber(Long.valueOf(request.getParameter("mobileNumber")));
        updateUser.setEmailId(request.getParameter("email"));
        updateUser.setAddress(request.getParameter("address"));
        updateUser.setUserName(request.getParameter("userName"));
        updateUser.setPassword(request.getParameter("password"));
        updateUser.setUserRole(request.getParameter("userRole"));
        updateUser.setUserStatus(request.getParameter("userStatus") != null ? "Active" : "Inactive");
        User creatorUser = (User) session.getAttribute("user");
        updateUser.setCreatorUser(creatorUser.getUserName());
        updateUser.setUserId(Long.valueOf(request.getParameter("user-id")));
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
        logger.info("update user details are "+updateUser);
        logger.info("update user permissions are "+permissions);
        UserManagementService service = new UserManagementService();
        Messages messages = service.updateUser(updateUser);
        session.setAttribute("message",service.convertToString(messages));
        if(messages.equals(Messages.USER_UPDATED)) {
            service.updatePermissions(permissions,updateUser.getUserId(),updateUser.getCreatorUser());
        }
        logger.info("update user details status message : "+messages);
        request.setAttribute("updateUser",updateUser);
        request.setAttribute("permissions",permissions);
        RequestDispatcher rd = request.getRequestDispatcher("update_user");
        rd.forward(request, response);
    }
}
