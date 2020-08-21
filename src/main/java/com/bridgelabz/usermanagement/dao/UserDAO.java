package com.bridgelabz.usermanagement.dao;

import com.bridgelabz.usermanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    String sql = "select first_name, last_name, email, id from user_details where email=? and password=?";

    public User getUserDetails(String userName, String password) {
        try {
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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

}
