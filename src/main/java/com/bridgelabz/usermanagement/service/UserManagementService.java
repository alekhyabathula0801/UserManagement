package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.model.NewUser;
import com.bridgelabz.usermanagement.model.User;

public class UserManagementService {

    public boolean addUser(NewUser newUser) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserDetailsByEmail(newUser.getEmailId());
        if(user != null)
            return false;
        return userDAO.addUser(newUser);
    }

}
