package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.NewUser;
import com.bridgelabz.usermanagement.model.User;

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

}
