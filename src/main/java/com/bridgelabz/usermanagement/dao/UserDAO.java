package com.bridgelabz.usermanagement.dao;

import com.bridgelabz.usermanagement.model.User;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserDAO {
    final static Logger logger = Logger.getLogger(UserDAO.class);
    String validateUserQuery = "select id, `user_profile_image` from user_details where user_name=? and password=? and status=?";
    String validateEmailQuery = "select first_name, last_name, user_name, password, id from user_details where email=?";
    String validateUserNameQuery = "select id from user_details where user_name=?";
    String addUserQuery = "insert into `user_details` (`first_name`, `middle_name`, `last_name`, `email`, `user_name`, " +
            "`date_of_birth`, `gender`, `country`, `country_code`, `phone`, `address` , `password`, `user_profile_image`," +
            "`user_role`, `creator_user`) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String addPermissions = "insert into `user_permissions` (`user_id`, `page_id`, `add`, `delete`, `modify`, `read`," +
            " `creator_user`) values (?,?,?,?,?,?,?)";
    String getPermissions = "select `add`, `delete`, `modify`, `read` from user_permissions where user_id=? and page_id=?";
    String getUserDetails = "select `first_name`, `middle_name`, `last_name`, `email`, `user_name`, `date_of_birth`," +
            " `gender`, `country`, `country_code`, `phone`, `address` , `password`, `user_role`, `user_profile_image`, `status` " +
            "from user_details where id=?" ;
    String updateUserDetails = "update `user_details` set `first_name`= ?, `middle_name`= ?, `last_name`= ?, `email`= ?," +
            " `user_name`= ?, `date_of_birth`= ?, `gender`= ?, `country`= ?, `country_code`= ?, `phone`= ?, `address`= ?," +
            " `password`= ?, `user_role`= ?, `updated_user` = ?, `status` = ? where (`id`=?)";
    String updatePermissions = "update `user_permissions` set `add` = ?, `delete` = ?, `modify` = ?, `read` = ?, " +
            "`updated_user` = ? where (`user_id` = ? and `page_id` = ?)";
    String deleteUserDetails = "delete from `user_details` where (`id` = ?)";
    String deletePermissions = "delete from `user_permissions` where (`user_id` = ?)";
    String updateImage = "update `user_details` set `user_profile_image` = ?, `updated_user` = ? where (`id` = ?)";
    String numberOfUsers = "select count(*) from `user_details`";
    String getLimitedUsers = "select first_name, last_name, email, date_of_birth, user_profile_image, user_role, id, status" +
            " from user_details  limit ?,?";
    String numberOfUsersBySearchWord = "select count(*) from user_details where first_name like ? or last_name like ?" +
            "or email like ?";
    String getLimitedUsersBySearchWord = "select first_name, last_name, email, date_of_birth, user_profile_image," +
            " user_role, id, status from user_details where first_name like ? or last_name like ? or email like ? limit ?,?";
    String setUserLogin = "update user_login_details set is_login = 1, last_login_date_time = now(), number_of_login_attempts = 0 where user_id = ? ";
    String getUserLastLoginTime = "select last_login_date_time from user_login_details where user_id = ?";
    String insertUserLoginDetails = "insert into user_login_details(user_id,is_login) values (?,1)";
    String setUserLogout = "update user_login_details set is_login = 0 where user_id = ? ";
    String getNumberOfLoginAttempts = "select number_of_login_attempts from user_details left join user_login_details " +
            "on user_details.id = user_login_details.user_id where user_name = ?";
    String setNumberOfLoginAttempts = "update user_details left join user_login_details " +
            "on user_details.id = user_login_details.user_id set number_of_login_attempts = ? where user_name = ?";
    String setUserStatus = "update user_details set status = ? where user_name = ?";
    String getDetailsOfUser = "select id, `user_profile_image` from user_details where user_name=?";

    Connection connection = new DatabaseConnection().getConnection();

    public User getUserDetails(String userName, String password) {
        try {
            User user = new User();
            PreparedStatement preparedStatement = connection.prepareStatement(validateUserQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3,"Active");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUserId(Long.valueOf(resultSet.getString(1)));
                user.setUserImage(getBase64Image(resultSet.getBlob(2)));
                user.setLastLoginStamp(getLastLoginTime(user.getUserId()));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return null;
    }

    public String getLastLoginTime(Long userId) {
        try {
            PreparedStatement statement = connection.prepareStatement(getUserLastLoginTime);
            statement.setLong(1,userId);
            ResultSet rs = statement.executeQuery();
            DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy hh:mm a");
            if(rs.next())
                return dateFormat.format(rs.getTimestamp(1));
            else {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
                return dtf.format(LocalDateTime.now());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
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
            logger.error("exception "+e.getMessage());
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
            logger.error("exception "+e.getMessage());
        }
        return null;
    }

    public boolean addUser(User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(addUserQuery);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getMiddleName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmailId());
            preparedStatement.setString(5, user.getUserName());
            preparedStatement.setString(6, user.getDateOfBirth());
            preparedStatement.setString(7, user.getGender());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getCountryCode());
            preparedStatement.setString(10, String.valueOf(user.getMobileNumber()));
            preparedStatement.setString(11, user.getAddress());
            preparedStatement.setString(12, user.getPassword());
            preparedStatement.setBlob(13, user.getUserImageInputStream());
            preparedStatement.setString(14, user.getUserRole());
            preparedStatement.setString(15, user.getCreatorUser());
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
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
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
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
            logger.error("exception "+e.getMessage());
        }
        return null;
    }

    public User getUserDetails(Long userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUserDetails);
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
                user.setUserImage(getBase64Image(resultSet.getBlob(14)));
                user.setUserStatus(resultSet.getString(15));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return user;
    }

    public boolean updatedUser(User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updateUserDetails);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getMiddleName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmailId());
            preparedStatement.setString(5, user.getUserName());
            preparedStatement.setString(6, user.getDateOfBirth());
            preparedStatement.setString(7, user.getGender());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getCountryCode());
            preparedStatement.setString(10, String.valueOf(user.getMobileNumber()));
            preparedStatement.setString(11, user.getAddress());
            preparedStatement.setString(12, user.getPassword());
            preparedStatement.setString(13, user.getUserRole());
            preparedStatement.setString(14, user.getCreatorUser());
            preparedStatement.setString(16, String.valueOf(user.getUserId()));
            preparedStatement.setString(15, user.getUserStatus());
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public boolean updatePermissions(Long userId, int pageId, int add, int delete, int modify, int read, String updatedUser) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updatePermissions);
            preparedStatement.setString(1, String.valueOf(add));
            preparedStatement.setString(2, String.valueOf(delete));
            preparedStatement.setString(3, String.valueOf(modify));
            preparedStatement.setString(4, String.valueOf(read));
            preparedStatement.setString(5, updatedUser);
            preparedStatement.setString(6, String.valueOf(userId));
            preparedStatement.setString(7, String.valueOf(pageId));
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public boolean deleteUserDetails(Long userId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(deleteUserDetails);
            preparedStatement.setString(1, String.valueOf(userId));
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public boolean deletePermissions(Long userId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(deletePermissions);
            preparedStatement.setString(1, String.valueOf(userId));
            return preparedStatement.executeUpdate()==6;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public boolean updateImage(Long userId, User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updateImage);
            preparedStatement.setBlob(1, user.getUserImageInputStream());
            preparedStatement.setString(2,user.getCreatorUser());
            preparedStatement.setString(3, String.valueOf(userId));
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public Long getNumberOfUsers() {
        return getNumberOfUsersByChoice(numberOfUsers);
    }

    public List<User> getLimitedUsers(int startNumber, int numberOfUsersToDisplay) {
        List<User> usersDetails = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getLimitedUsers);
            preparedStatement.setInt(1, startNumber);
            preparedStatement.setInt(2, numberOfUsersToDisplay);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserFullName(resultSet.getString(1) + " "+resultSet.getString(2));
                user.setEmailId(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getString(4));
                user.setUserRole(resultSet.getString(6));
                user.setUserId(resultSet.getLong(7));
                user.setUserStatus(resultSet.getString(8));
                user.setUserImage(getBase64Image(resultSet.getBlob(5)));
                usersDetails.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return usersDetails;
    }

    public Long getNumberOfUsers(String searchWord) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(numberOfUsersBySearchWord);
            preparedStatement.setString(1,searchWord);
            preparedStatement.setString(2,searchWord);
            preparedStatement.setString(3,searchWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return null;
    }

    public List<User> getLimitedUsers(int startNumber, int numberOfUsersToDisplay, String searchWord) {
        List<User> usersDetails = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getLimitedUsersBySearchWord);
            preparedStatement.setString(1,searchWord);
            preparedStatement.setString(2,searchWord);
            preparedStatement.setString(3,searchWord);
            preparedStatement.setInt(4, startNumber);
            preparedStatement.setInt(5, numberOfUsersToDisplay);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserFullName(resultSet.getString(1) + " "+resultSet.getString(2));
                user.setEmailId(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getString(4));
                user.setUserRole(resultSet.getString(6));
                user.setUserId(resultSet.getLong(7));
                user.setUserStatus(resultSet.getString(8));
                user.setUserImage(getBase64Image(resultSet.getBlob(5)));
                usersDetails.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return usersDetails;
    }

    public String getBase64Image(Blob blob) {
        InputStream inputStream = null;
        try {
            inputStream = blob.getBinaryStream();
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
            return image;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return null;
    }

    public boolean setUserLogin(Long userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(setUserLogin);
            preparedStatement.setLong(1, userId);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public boolean insertUserLoginDetails(Long userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserLoginDetails);
            preparedStatement.setLong(1, userId);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public boolean setUserLogout(Long userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(setUserLogout);
            preparedStatement.setLong(1, userId);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public Long getNumberOfUsersByChoice(String sqlQuery) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return null;
    }

    public Integer getNumberOfLoginAttempts(String userName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getNumberOfLoginAttempts);
            preparedStatement.setString(1,userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return null;
    }

    public boolean setNumberOfLoginAttempts(String userName, int numberOfAttempts) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(setNumberOfLoginAttempts);
            preparedStatement.setInt(1,numberOfAttempts);
            preparedStatement.setString(2,userName);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public boolean setUserStatus(String status, String userName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(setUserStatus);
            preparedStatement.setString(1,status);
            preparedStatement.setString(2,userName);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public User getDetailsOfUser(String userName) {
        try {
            User user = new User();
            PreparedStatement preparedStatement = connection.prepareStatement(getDetailsOfUser);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUserId(Long.valueOf(resultSet.getString(1)));
                user.setUserImage(getBase64Image(resultSet.getBlob(2)));
                user.setLastLoginStamp(getLastLoginTime(user.getUserId()));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return null;
    }
}
