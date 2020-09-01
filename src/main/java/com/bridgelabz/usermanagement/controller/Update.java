package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.Permissions;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/Update")
@MultipartConfig(maxFileSize = 16177215)
public class Update extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        com.bridgelabz.usermanagement.model.NewUser user = new com.bridgelabz.usermanagement.model.NewUser();
        user.setFirstName(request.getParameter("firstName"));
        user.setMiddleName(request.getParameter("middleName"));
        user.setLastName(request.getParameter("lastName"));
        user.setDateOfBirth(request.getParameter("dateOfBirth"));
        user.setGender(request.getParameter("gender"));
        user.setCountry(request.getParameter("country"));
        user.setCountryCode(request.getParameter("countryCode"));
        user.setMobileNumber(Long.valueOf(request.getParameter("mobileNumber")));
        user.setEmailId(request.getParameter("email"));
        user.setAddress(request.getParameter("address"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setUserRole(request.getParameter("userRole"));
        User creatorUser = (User) session.getAttribute("user");
        user.setCreatorUser(creatorUser.getUserName());
        user.setUserId(Long.valueOf(request.getParameter("user-id")));

        InputStream inputStream = null;
        Part filePart = request.getPart("new-user-profile-image");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        } else {
            inputStream = new FileInputStream("C:\\Users\\arun kumar\\IdeaProjects\\UserManagementApp\\src\\main\\webapp\\assests\\default-user-image.png");
        }

        user.setUserImage(inputStream);

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

        UserManagementService service = new UserManagementService();
        Messages messages = service.updateUser(user);
        session.setAttribute("message",service.convertToString(messages));
        if(messages.equals(Messages.USER_UPDATED)) {
            session.setAttribute("message",service.convertToString(messages));
        } else {
            request.setAttribute("user",user);
            request.setAttribute("permissions",permissions);
        }
        request.setAttribute("user",user);
        RequestDispatcher rd = request.getRequestDispatcher("update_user");
        rd.forward(request, response);
    }

}
