package com.bridgelabz.usermanagement.dao;

import com.bridgelabz.usermanagement.model.NewUser;
import com.bridgelabz.usermanagement.model.User;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserDAO {
//    UPDATE `user_management`.`user_details` SET `user_name` = 'AnuAngel' WHERE (`id` = '21');
    String validateUserQuery = "select id from user_details where user_name=? and password=?";
    String validateEmailQuery = "select first_name, last_name, user_name, password, id from user_details where email=?";
    String validateUserNameQuery = "select id from user_details where user_name=?";
    String addUserQuery = "insert into `user_details` (`first_name`, `middle_name`, `last_name`, `email`, `user_name`, " +
            "`date_of_birth`, `gender`, `country`, `country_code`, `phone`, `address` , `password`, `user_profile_image`," +
            "`user_role`, `creator_user`) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String addPermissions = "insert into `user_permissions` (`user_id`, `page_id`, `add`, `delete`, `modify`, `read`," +
            " `creator_user`) values (?,?,?,?,?,?,?)";
    String getPermissions = "select `add`, `delete`, `modify`, `read` from user_permissions where user_id=? and page_id=?";
    String getAllUsers = "select first_name, last_name, email, date_of_birth, user_profile_image, user_role,id from user_details";
    String getAllUserDetails = "select `first_name`, `middle_name`, `last_name`, `email`, `user_name`, `date_of_birth`," +
            " `gender`, `country`, `country_code`, `phone`, `address` , `password`, `user_role` from user_details where id=?" ;
    String updateUserDetails = "update `user_details` set `first_name`= ?, `middle_name`= ?, `last_name`= ?, `email`= ?," +
            " `user_name`= ?, `date_of_birth`= ?, `gender`= ?, `country`= ?, `country_code`= ?, `phone`= ?, `address`= ?," +
            " `password`= ?, `user_profile_image`= ?,`user_role`= ?, `updated_user` = ? where (`id`=?)";
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

    public User getUserDetailsByUserName(String userName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(validateUserNameQuery);
            preparedStatement.setString(1, userName);
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
            preparedStatement.setBlob(13,newUser.getUserImage());
            preparedStatement.setString(14, newUser.getUserRole());
            preparedStatement.setString(15, newUser.getCreatorUser());
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean addPermissions(Long userId, int pageId, int add, int delete, int modify, int read,String creatorUser) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(addPermissions);
            preparedStatement.setString(1, String.valueOf(userId));
            preparedStatement.setString(2, String.valueOf(pageId));
            preparedStatement.setString(3, String.valueOf(add));
            preparedStatement.setString(4, String.valueOf(delete));
            preparedStatement.setString(5, String.valueOf(modify));
            preparedStatement.setString(6, String.valueOf(read));
            preparedStatement.setString(7, creatorUser);
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Integer> getPermissions(int pageId, Long userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getPermissions);
            preparedStatement.setString(1, String.valueOf(userId));
            preparedStatement.setString(2, String.valueOf(pageId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                List<Integer> permissions = new ArrayList<>();
                permissions.add(Integer.valueOf((resultSet.getString(1))));
                permissions.add(Integer.valueOf((resultSet.getString(2))));
                permissions.add(Integer.valueOf((resultSet.getString(3))));
                permissions.add(Integer.valueOf((resultSet.getString(4))));
                return permissions;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> usersDetails = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllUsers);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUserFullName(resultSet.getString(1) + " "+resultSet.getString(2));
                user.setEmailId(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getString(4));
                user.setUserRole(resultSet.getString(6));
                user.setUserId(resultSet.getLong(7));

                Blob blob = resultSet.getBlob(5);
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                String image = Base64.getEncoder().encodeToString(imageBytes);
                inputStream.close();
                outputStream.close();
                user.setUserImage(image);
                usersDetails.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersDetails;
    }

    public NewUser getAllUserDetails(Long userId) {
        NewUser user = new NewUser();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllUserDetails);
            preparedStatement.setString(1, String.valueOf(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setFirstName(resultSet.getString(1));
                user.setMiddleName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setEmailId(resultSet.getString(4));
                user.setUserName(resultSet.getString(5));
                user.setDateOfBirth(resultSet.getString(6));
                user.setGender(resultSet.getString(7));
                user.setCountry(resultSet.getString(8));
                user.setCountryCode(resultSet.getString(9));
                user.setMobileNumber(resultSet.getLong(10));
                user.setAddress(resultSet.getString(11));
                user.setPassword(resultSet.getString(12));
                user.setUserRole(resultSet.getString(13));
                user.setUserId(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updatedUser(NewUser newUser) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updateUserDetails);
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
            preparedStatement.setBlob(13,newUser.getUserImage());
            preparedStatement.setString(14, newUser.getUserRole());
            preparedStatement.setString(15, newUser.getCreatorUser());
            preparedStatement.setString(16, String.valueOf(newUser.getUserId()));
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
