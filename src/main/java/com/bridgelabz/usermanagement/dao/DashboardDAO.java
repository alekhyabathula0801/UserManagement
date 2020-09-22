package com.bridgelabz.usermanagement.dao;

import com.bridgelabz.usermanagement.model.Country;
import com.bridgelabz.usermanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashboardDAO {

    Connection connection = new DatabaseConnection().getConnection();

    String getNumberOfUsersRegisteredByAgeRange = "select count(id) from user_details where " +
            "datediff(curdate(),date_of_birth) between ? and ?";
    String getNumberOfUsersRegisteredInCurrentYearByAgeRange = "select count(id) from user_details where " +
            "year(creator_stamp) = year(curdate()) and datediff(curdate(),date_of_birth) between ? and ? ";
    String getNumberOfUsersRegisteredInCurrentMonthByAgeRange = "select count(id) from user_details where " +
            "year(creator_stamp) = year(curdate()) and datediff(curdate(),date_of_birth) between ? and ? " +
            "and month(creator_stamp) = month(curdate())";
    String getNumberOfUsersByStatus = "select count(*) from user_details where status like ?";
    String getNumberOfUsersOnline = "select count(*) from user_login_details where is_login = 1";
    String numberOfUsersInCurrentYear = "select count(*) from `user_details` where year(creator_stamp) = year(curdate())";
    String numberOfUsersInCurrentMonth = "select count(*) from `user_details` where year(creator_stamp) = year(curdate())" +
            " and month(creator_stamp) = month(curdate())";
    String numberOfUsers = "select count(*) from `user_details`";
    String getRecentRegistration = "select first_name, last_name, id, `user_profile_image`, creator_stamp from" +
            " user_details order by id desc limit 0,?";
    String getNumberOfUsersByGender = "select count(*) from user_details where gender like ?";
    String getNumberOfUsersByGenderInCurrentYear = "select count(*) from user_details where gender like ? and " +
            "year(creator_stamp) = year(curdate())";
    String getNumberOfUsersByGenderInCurrentMonth = "select count(*) from user_details where gender like ? and " +
            "year(creator_stamp) = year(curdate()) and month(creator_stamp) = month(curdate())";
    String getAllTimeRegisteredUsers = "select count(id) as number_of_users , date_format(creator_stamp,\"%b.%Y\")" +
            " as date  from user_details group by date_format(creator_stamp,\"%m %Y\")";
    String getRegisteredUsersInCurrentYear = "select count(id) as number_of_users , " +
            "date_format(creator_stamp,\"%b.%Y\") as date  from user_details where year(creator_stamp) = year(curdate())" +
            "  group by date_format(creator_stamp,\"%m %y\")";
    String getRegisteredUsersInCurrentMonth = "select count(id) as number_of_users , " +
            "date_format(creator_stamp,\"%e.%b.%Y\") as date  from user_details where  year(creator_stamp) = year(curdate())" +
            " and month(creator_stamp) = month(curdate()) group by date_format(creator_stamp,\"%d %m\")";
    String getCountriesWithMaximumUsers = "select country, count(id) from user_details group by country" +
            " order by count(id) desc limit ?,?";
    String getCountriesWithMaximumUsersInCurrentYear = "select country, count(id) from user_details" +
            " where year(creator_stamp) = year(curdate()) group by country order by count(id) desc limit ?,?";
    String getCountriesWithMaximumUsersInCurrentMonth = "select country, count(id) from user_details" +
            " where year(creator_stamp) = year(curdate()) and month(creator_stamp) = month(curdate())" +
            " group by country order by count(id) desc limit ?,?";
    String getCountriesWithMaximumUsersBySearchWord = "select country, count(id) from user_details where country like ?" +
            " group by country order by count(id) desc limit ?,?";
    String getCountriesWithMaximumUsersInCurrentYearBySearchWord = "select country, count(id) from user_details" +
            " where year(creator_stamp) = year(curdate()) and country like ? group by country order by count(id) desc limit ?,?";
    String getCountriesWithMaximumUsersInCurrentMonthBySearchWord = "select country, count(id) from user_details" +
            " where year(creator_stamp) = year(curdate()) and month(creator_stamp) = month(curdate()) " +
            " and country like ? group by country order by count(id) desc limit ?,?";
    String numberOfCountries = "select count(distinct country) from user_details";
    String numberOfCountriesInCurrentYear = "select count(distinct country) from user_details " +
            "where year(creator_stamp) = year(curdate())";
    String numberOfCountriesInCurrentMonth = "select count(distinct country) from user_details " +
            "where year(creator_stamp) = year(curdate()) and month(creator_stamp) = month(curdate())";
    String numberOfCountriesBySearchWord = "select count(distinct country) from user_details where country like ?";
    String numberOfCountriesInCurrentYearBySearchWord = "select count(distinct country) from user_details " +
            "where year(creator_stamp) = year(curdate()) and country like ?";
    String numberOfCountriesInCurrentMonthBySearchWord = "select count(distinct country) from user_details " +
            "where year(creator_stamp) = year(curdate()) and month(creator_stamp) = month(curdate()) and country like ?";

    public Long getNumberOfCountries(int userChoice) {
        try {
            PreparedStatement preparedStatement = null;
            switch (userChoice) {
                case 1:
                    preparedStatement = connection.prepareStatement(numberOfCountriesInCurrentYear);
                case 2:
                    preparedStatement = connection.prepareStatement(numberOfCountriesInCurrentMonth);
                default:
                    preparedStatement = connection.prepareStatement(numberOfCountries);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long getNumberOfCountries(int userChoice, String searchWord) {
        try {
            PreparedStatement preparedStatement = null;
            switch (userChoice) {
                case 1:
                    preparedStatement = connection.prepareStatement(numberOfCountriesInCurrentYearBySearchWord);
                case 2:
                    preparedStatement = connection.prepareStatement(numberOfCountriesInCurrentMonthBySearchWord);
                default:
                    preparedStatement = connection.prepareStatement(numberOfCountriesBySearchWord);
            }
            preparedStatement.setString(1,searchWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Country> getCountriesWithMaximumUsers(int startNumber, int numberOfCountries, int userChoice, String searchWord) {
        List<Country> countriesWithMaximumUsers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = null;
            switch (userChoice) {
                case 1:
                    preparedStatement = connection.prepareStatement(getCountriesWithMaximumUsersInCurrentYearBySearchWord);
                    break;
                case 2:
                    preparedStatement = connection.prepareStatement(getCountriesWithMaximumUsersInCurrentMonthBySearchWord);
                    break;
                default:
                    preparedStatement = connection.prepareStatement(getCountriesWithMaximumUsersBySearchWord);
                    break;
            }
            preparedStatement.setString(1,searchWord);
            preparedStatement.setInt(2,startNumber);
            preparedStatement.setInt(3,numberOfCountries);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country();
                country.setCountry(resultSet.getString(1));
                country.setNumberOfUsers(resultSet.getInt(2));
                countriesWithMaximumUsers.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countriesWithMaximumUsers;
    }

    public List<Country> getCountriesWithMaximumUsers(int startNumber, int numberOfUsers, int userChoice) {
        List<Country> countriesWithMaximumUsers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = null;
            switch (userChoice) {
                case 1:
                    preparedStatement = connection.prepareStatement(getCountriesWithMaximumUsersInCurrentYear);
                    break;
                case 2:
                    preparedStatement = connection.prepareStatement(getCountriesWithMaximumUsersInCurrentMonth);
                    break;
                default:
                    preparedStatement = connection.prepareStatement(getCountriesWithMaximumUsers);
                    break;
            }
            preparedStatement.setInt(1,startNumber);
            preparedStatement.setInt(2,numberOfUsers);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country();
                country.setCountry(resultSet.getString(1));
                country.setNumberOfUsers(resultSet.getInt(2));
                countriesWithMaximumUsers.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countriesWithMaximumUsers;
    }

    public Map<String,Long> getNumberOfRegisteredUsers(String sqlQuery) {
        Map<String,Long> numberOfRegisteredUsers = new LinkedHashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                numberOfRegisteredUsers.put(resultSet.getString(2),resultSet.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfRegisteredUsers;
    }

    public Map<String,Long> getAllTimeRegisteredUsers() {
        return getNumberOfRegisteredUsers(getAllTimeRegisteredUsers);
    }

    public Map<String, Long> getRegisteredUsersInCurrentYear() {
        return getNumberOfRegisteredUsers(getRegisteredUsersInCurrentYear);
    }

    public Map<String, Long> getRegisteredUsersInCurrentMonth() {
        return getNumberOfRegisteredUsers(getRegisteredUsersInCurrentMonth);
    }

    public Long getNumberOfUsersByGender(String gender,int userChoice) {
        try {
            PreparedStatement preparedStatement = null;
            switch (userChoice) {
                case 0:
                    preparedStatement = connection.prepareStatement(getNumberOfUsersByGender);
                    break;
                case 1:
                    preparedStatement = connection.prepareStatement(getNumberOfUsersByGenderInCurrentYear);
                    break;
                case 2:
                    preparedStatement = connection.prepareStatement(getNumberOfUsersByGenderInCurrentMonth);
                    break;
            }
            preparedStatement.setString(1,gender);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getRecentRegistrations(int numberOfUsers) {
        List<User> usersDetails = new ArrayList<>();
        try {
            DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy hh:mm a");
            PreparedStatement preparedStatement = connection.prepareStatement(getRecentRegistration);
            preparedStatement.setInt(1,numberOfUsers);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserFullName(resultSet.getString(1) + " "+resultSet.getString(2));
                user.setUserId(resultSet.getLong(3));
                user.setCreatorStamp(dateFormat.format(resultSet.getTimestamp(5)));
                user.setUserImage(new UserDAO().getBase64Image(resultSet.getBlob(4)));
                usersDetails.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersDetails;
    }

    public Map<String,Integer> getNumberOfUsersByAge() {
        return getNumberOfUsersByAge(getNumberOfUsersRegisteredByAgeRange);
    }

    public Map<String,Integer> getNumberOfUsersByAgeInCurrentYear() {
        return getNumberOfUsersByAge(getNumberOfUsersRegisteredInCurrentYearByAgeRange);
    }

    public Map<String,Integer> getNumberOfUsersByAgeInCurrentMonth() {
        return getNumberOfUsersByAge(getNumberOfUsersRegisteredInCurrentMonthByAgeRange);
    }

    public Map<String,Integer> getNumberOfUsersByAge(String sqlQuery) {
        Map<String,Integer> age = new LinkedHashMap();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,0);
            preparedStatement.setInt(2,18*365);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getInt(1) != 0)
                    age.put("Under 18",resultSet.getInt(1));
            }
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,18*365);
            preparedStatement.setInt(2,23*365);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getInt(1) != 0)
                    age.put("18-22",resultSet.getInt(1));
            }
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,23*365);
            preparedStatement.setInt(2,28*365);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getInt(1) != 0)
                    age.put("23-27",resultSet.getInt(1));
            }
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,28*365);
            preparedStatement.setInt(2,33*365);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getInt(1) != 0)
                    age.put("28-32",resultSet.getInt(1));
            }
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,33*365);
            preparedStatement.setInt(2,38*365);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getInt(1) != 0)
                    age.put("33-37",resultSet.getInt(1));
            }
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,38*365);
            preparedStatement.setInt(2,42*365);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getInt(1) != 0)
                    age.put("38-42",resultSet.getInt(1));
            }
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,42*365);
            preparedStatement.setInt(2,100*365);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getInt(1) != 0)
                    age.put("Over 42",resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age;
    }

    public Long getNumberOfUsersByStatus(String status) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getNumberOfUsersByStatus);
            preparedStatement.setString(1,status);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long getNumberOfUsersOnline() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getNumberOfUsersOnline);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long getNumberOfUsers(int userChoice) {
        switch (userChoice) {
            case 0:
                return new UserDAO().getNumberOfUsersByChoice(numberOfUsers);
            case 1:
                return new UserDAO().getNumberOfUsersByChoice(numberOfUsersInCurrentYear);
            case 2:
                return new UserDAO().getNumberOfUsersByChoice(numberOfUsersInCurrentMonth);
        }
        return null;
    }

}
