package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.NewUser;
import com.bridgelabz.usermanagement.model.Permissions;
import com.bridgelabz.usermanagement.model.User;

import java.util.List;

import static com.bridgelabz.usermanagement.enumeration.Messages.*;

public class UserManagementService {

    public Messages addUser(NewUser newUser) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserDetailsByEmail(newUser.getEmailId());
        if(user != null)
            return EMAIL_EXISTS;
        User user1 = userDAO.getUserDetailsByUserName(newUser.getUserName());
        if(user1 != null)
            return USER_NAME_EXISTS;
        if(userDAO.addUser(newUser))
            return USER_ADDED;
        return SERVER_SIDE_PROBLEM_TRY_AGAIN_LATER;
    }

    public void addPermissions(Permissions permissions, String userName, String creatorUser) {
        UserDAO userDAO = new UserDAO();
        Long userId = userDAO.getUserDetailsByUserName(userName).getUserId();
        userDAO.addPermissions(userId,1,permissions.getDashboardAdd(),permissions.getDashboardDelete(),
                permissions.getDashboardModify(),permissions.getDashboardRead(),creatorUser);
        userDAO.addPermissions(userId,2,permissions.getSettingsAdd(),permissions.getSettingsDelete(),
                permissions.getSettingsModify(),permissions.getSettingsRead(),creatorUser);
        userDAO.addPermissions(userId,3,permissions.getUserInformationAdd(),permissions.getUserInformationDelete(),
                permissions.getUserInformationModify(),permissions.getUserInformationRead(),creatorUser);
        userDAO.addPermissions(userId,4,permissions.getWebpage1Add(),permissions.getWebpage1Delete(),
                permissions.getWebpage1Modify(),permissions.getWebpage1Read(),creatorUser);
        userDAO.addPermissions(userId,5,permissions.getWebpage2Add(),permissions.getWebpage2Delete(),
                permissions.getWebpage2Modify(),permissions.getWebpage2Read(),creatorUser);
        userDAO.addPermissions(userId,6,permissions.getWebpage3Add(),permissions.getWebpage3Delete(),
                permissions.getWebpage3Modify(),permissions.getWebpage3Read(),creatorUser);
    }

    public List<Integer> getPermissions(int pageId,Long userId) {
        UserDAO userDAO = new UserDAO();
        List<Integer> permissions = userDAO.getPermissions(pageId,userId);
        if (permissions.contains(1)) {
            return permissions;
        }
        return null;
    }

    public String convertToString(Messages messages) {
        String convertedMessage = "";
        String message = messages.toString();
        for(int index=0; index<message.length(); index++) {
            if ('_' == message.charAt(index))
                convertedMessage = convertedMessage + " ";
            else
                convertedMessage = convertedMessage + message.charAt(index);
        }
        return convertedMessage.toLowerCase();
    }

    public List<User> getAllUsers() {
        return new UserDAO().getAllUsers();
    }
}
