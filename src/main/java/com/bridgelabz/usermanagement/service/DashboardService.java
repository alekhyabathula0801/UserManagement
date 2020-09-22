package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dao.DashboardDAO;
import com.bridgelabz.usermanagement.model.Country;
import com.bridgelabz.usermanagement.model.User;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class DashboardService {
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public Map<String,Integer> getNumberOfUsersByAge() {
        return new DashboardDAO().getNumberOfUsersByAge();
    }

    public Map<String,Integer> getNumberOfUsersByAgeInCurrentYear() {
        return new DashboardDAO().getNumberOfUsersByAgeInCurrentYear();
    }

    public Map<String,Integer> getNumberOfUsersByAgeCurrentMonth() {
        return new DashboardDAO().getNumberOfUsersByAgeInCurrentMonth();
    }

    public Long getNumberOfUsersByStatus(String status) {
        return new DashboardDAO().getNumberOfUsersByStatus(status);
    }

    public Long getNumberOfUsersOnline() {
        return new DashboardDAO().getNumberOfUsersOnline();
    }

    private Long getNumberOfUsers(int userChoice) {
        return new DashboardDAO().getNumberOfUsers(userChoice);
    }

    public List<User> getRecentRegistrations(int numberOfUsers) {
        return new DashboardDAO().getRecentRegistrations(numberOfUsers);
    }

    public Double getFemaleRatio(int userChoice) {
        Long numberOfFemaleUsers = new DashboardDAO().getNumberOfUsersByGender("Female",userChoice);
        Long numberOfUsers = getNumberOfUsers(userChoice);
        if(numberOfFemaleUsers != null & numberOfUsers != 0)
            return Double.parseDouble(decimalFormat.format(numberOfFemaleUsers*100.0/numberOfUsers));
        return null;
    }

    public Double getMaleRatio(int userChoice) {
        if(getFemaleRatio(userChoice) != null)
            return Double.parseDouble(decimalFormat.format(100 - getFemaleRatio(userChoice)));
        return null;
    }

    public Map<String, Long> getAllTimeRegisteredUsers() {
        return new DashboardDAO().getAllTimeRegisteredUsers();
    }

    public Map<String, Long> getRegisteredUsersInCurrentYear() {
        return new DashboardDAO().getRegisteredUsersInCurrentYear();
    }

    public Map<String, Long> getAllTimeRegisteredUsersInCurrentMonth() {
        return new DashboardDAO().getRegisteredUsersInCurrentMonth();
    }

    public List<Country> getCountriesWithMaximumUsers(int pageId, int numberOfUsers, int userChoice) {
        int startNumber = (pageId-1)*numberOfUsers;
        return new DashboardDAO().getCountriesWithMaximumUsers(startNumber,numberOfUsers, userChoice);
    }

    public List<Country> getCountriesWithMaximumUsers(int pageId, int numberOfCountries, int userChoice, String searchWord) {
        int startNumber = (pageId-1)*numberOfCountries;
        return new DashboardDAO().getCountriesWithMaximumUsers(startNumber,numberOfCountries, userChoice,"%"+searchWord+"%");
    }

    public Long getNumberOfCountries(int userChoice) {
        return new DashboardDAO().getNumberOfCountries(userChoice);
    }

    public Long getCountriesWithMaximumUsers(String searchWord, int userChoice) {
        return new DashboardDAO().getNumberOfCountries(userChoice,"%"+searchWord+"%");
    }

}
