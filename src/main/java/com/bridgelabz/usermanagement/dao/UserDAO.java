package com.bridgelabz.usermanagement.dao;

import com.bridgelabz.usermanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    String validateUserQuery = "select first_name, last_name, email, id from user_details where email=? and password=?";
    String validateEmailQuery = "select first_name, last_name, password, id from user_details where email=?";
    Connection connection = new DatabaseConnection().getConnection();

    public User getUserDetails(String userName, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(validateUserQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString(1) + " "+resultSet.getString(2));
                user.setEmailId(resultSet.getString(3));
                user.setUserId(Long.valueOf(resultSet.getString(4)));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserDetailsByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(validateEmailQuery);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString(1) + " "+resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setUserId(Long.valueOf(resultSet.getString(4)));
                user.setEmailId(email);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
