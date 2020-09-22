package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.Permissions;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/NewUser")
@MultipartConfig(maxFileSize = 16177215)
public class NewUser extends HttpServlet {
    final static Logger logger = Logger.getLogger(NewUser.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("request to add new user received");
        HttpSession session = request.getSession();
        User newUser = new User();
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

        InputStream inputStream = null;
        Part filePart = request.getPart("new-user-profile-image");
        if (filePart.getSize() != 0) {
            inputStream = filePart.getInputStream();
        } else {
            inputStream = new FileInputStream("C:\\Users\\arun kumar\\IdeaProjects\\UserManagementApp\\src\\main\\webapp\\assests\\default-user-image.png");
        }

        newUser.setUserImageInputStream(inputStream);

        Permissions newUserPermissions = new Permissions();
        newUserPermissions.setDashboardAdd(Integer.parseInt(request.getParameter("dashboard-add")));
        newUserPermissions.setDashboardDelete(Integer.parseInt(request.getParameter("dashboard-delete")));
        newUserPermissions.setDashboardModify(Integer.parseInt(request.getParameter("dashboard-modify")));
        newUserPermissions.setDashboardRead(Integer.parseInt(request.getParameter("dashboard-read")));
        newUserPermissions.setSettingsAdd(Integer.parseInt(request.getParameter("settings-add")));
        newUserPermissions.setSettingsDelete(Integer.parseInt(request.getParameter("settings-delete")));
        newUserPermissions.setSettingsModify(Integer.parseInt(request.getParameter("settings-modify")));
        newUserPermissions.setSettingsRead(Integer.parseInt(request.getParameter("settings-read")));
        newUserPermissions.setUserInformationAdd(Integer.parseInt(request.getParameter("userinfo-add")));
        newUserPermissions.setUserInformationDelete(Integer.parseInt(request.getParameter("userinfo-delete")));
        newUserPermissions.setUserInformationModify(Integer.parseInt(request.getParameter("userinfo-modify")));
        newUserPermissions.setUserInformationRead(Integer.parseInt(request.getParameter("userinfo-read")));
        newUserPermissions.setWebpage1Add(Integer.parseInt(request.getParameter("webpage1-add")));
        newUserPermissions.setWebpage1Delete(Integer.parseInt(request.getParameter("webpage1-delete")));
        newUserPermissions.setWebpage1Modify(Integer.parseInt(request.getParameter("webpage1-modify")));
        newUserPermissions.setWebpage1Read(Integer.parseInt(request.getParameter("webpage1-read")));
        newUserPermissions.setWebpage2Add(Integer.parseInt(request.getParameter("webpage2-add")));
        newUserPermissions.setWebpage2Delete(Integer.parseInt(request.getParameter("webpage2-delete")));
        newUserPermissions.setWebpage2Modify(Integer.parseInt(request.getParameter("webpage2-modify")));
        newUserPermissions.setWebpage2Read(Integer.parseInt(request.getParameter("webpage2-read")));
        newUserPermissions.setWebpage3Add(Integer.parseInt(request.getParameter("webpage3-add")));
        newUserPermissions.setWebpage3Delete(Integer.parseInt(request.getParameter("webpage3-delete")));
        newUserPermissions.setWebpage3Modify(Integer.parseInt(request.getParameter("webpage3-modify")));
        newUserPermissions.setWebpage3Read(Integer.parseInt(request.getParameter("webpage3-read")));
        logger.info("new user details are "+newUser);
        logger.info("new user permissions are "+newUserPermissions);
        UserManagementService service = new UserManagementService();
        Messages messages = service.addUser(newUser);
        session.setAttribute("message",service.convertToString(messages));
        if(messages.equals(Messages.USER_ADDED)) {
            service.addPermissions(newUserPermissions, newUser.getUserName(),newUser.getCreatorUser());
            logger.info("user added successfully");
        } else {
            logger.warn("adding new user failed. message : "+messages);
            request.setAttribute("newUser",newUser);
            request.setAttribute("newUserPermissions",newUserPermissions);
        }
        RequestDispatcher rd = request.getRequestDispatcher("new_user");
        rd.forward(request, response);
    }
}
