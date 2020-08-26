package com.bridgelabz.usermanagement.dao;

import com.bridgelabz.usermanagement.model.NewUser;
import com.bridgelabz.usermanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    String validateUserQuery = "select id from user_details where user_name=? and password=?";
    String validateEmailQuery = "select first_name, last_name, user_name, password, id from user_details where email=?";
    String addUserQuery = "insert into `user_details` (`first_name`, `middle_name`, `last_name`, `email`, `user_name`, `date_of_birth`, `gender`, `country`, `country_code`, `phone`, `address` , `password`,`user_role`, `creator_user`) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    Connection connection = new DatabaseConnection().getConnection();

    public User getUserDetails(String userName, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(validateUserQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(Long.valueOf(resultSet.getString(1)));
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
                user.setUserFullName(resultSet.getString(1) + " "+resultSet.getString(2));
                user.setUserName(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setUserId(Long.valueOf(resultSet.getString(5)));
                user.setEmailId(email);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(NewUser newUser) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(addUserQuery);
            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getMiddleName());
            preparedStatement.setString(3, newUser.getLastName());
            preparedStatement.setString(4, newUser.getEmailId());
            preparedStatement.setString(5, newUser.getUserName());
            preparedStatement.setString(6, newUser.getDateOfBirth());
            preparedStatement.setString(7, newUser.getGender());
            preparedStatement.setString(8, newUser.getCountry());
            preparedStatement.setString(9, newUser.getCountryCode());
            preparedStatement.setString(10, String.valueOf(newUser.getMobileNumber()));
            preparedStatement.setString(11, newUser.getAddress());
            preparedStatement.setString(12, newUser.getPassword());
            preparedStatement.setString(13, newUser.getUserRole());
            preparedStatement.setString(14, newUser.getCreatorUser());
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
