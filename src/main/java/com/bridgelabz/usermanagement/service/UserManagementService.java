package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.Country;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.model.Permissions;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static com.bridgelabz.usermanagement.enumeration.Messages.*;

public class UserManagementService {

    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public Messages addUser(User user) {
        UserDAO userDAO = new UserDAO();
        User user1 = userDAO.getUserDetailsByEmail(user.getEmailId());
        if(user1 != null)
            return EMAIL_EXISTS;
        User user2 = userDAO.getUserDetailsByUserName(user.getUserName());
        if(user2 != null)
            return USER_NAME_EXISTS;
        if(userDAO.addUser(user))
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

    public User getDetailsOfUser(Long userId) {
        return new UserDAO().getUserDetails(userId);
    }

    public Permissions getAllPermissions(Long userId) {
        Permissions permissions = new Permissions();
        UserDAO userDAO = new UserDAO();
        List<Integer> dashboardPermissions = userDAO.getPermissions(1,userId);
        permissions.setDashboardAdd(dashboardPermissions.get(0));
        permissions.setDashboardDelete(dashboardPermissions.get(1));
        permissions.setDashboardModify(dashboardPermissions.get(2));
        permissions.setDashboardRead(dashboardPermissions.get(3));
        List<Integer> settingsPermissions = userDAO.getPermissions(2,userId);
        permissions.setSettingsAdd(settingsPermissions.get(0));
        permissions.setSettingsDelete(settingsPermissions.get(1));
        permissions.setSettingsModify(settingsPermissions.get(2));
        permissions.setSettingsRead(settingsPermissions.get(3));
        List<Integer> userinfoPermissions = userDAO.getPermissions(3,userId);
        permissions.setUserInformationAdd(userinfoPermissions.get(0));
        permissions.setUserInformationDelete(userinfoPermissions.get(1));
        permissions.setUserInformationModify(userinfoPermissions.get(2));
        permissions.setUserInformationRead(userinfoPermissions.get(3));
        List<Integer> webpage1Permissions = userDAO.getPermissions(4,userId);
        permissions.setWebpage1Add(webpage1Permissions.get(0));
        permissions.setWebpage1Delete(webpage1Permissions.get(1));
        permissions.setWebpage1Modify(webpage1Permissions.get(2));
        permissions.setWebpage1Read(webpage1Permissions.get(3));
        List<Integer> webpage2Permissions = userDAO.getPermissions(5,userId);
        permissions.setWebpage2Add(webpage2Permissions.get(0));
        permissions.setWebpage2Delete(webpage2Permissions.get(1));
        permissions.setWebpage2Modify(webpage2Permissions.get(2));
        permissions.setWebpage2Read(webpage2Permissions.get(3));
        List<Integer> webpage3Permissions = userDAO.getPermissions(6,userId);
        permissions.setWebpage3Add(webpage3Permissions.get(0));
        permissions.setWebpage3Delete(webpage3Permissions.get(1));
        permissions.setWebpage3Modify(webpage3Permissions.get(2));
        permissions.setWebpage3Read(webpage3Permissions.get(3));
        return permissions;
    }

    public Messages updateUser(User user) {
        UserDAO userDAO = new UserDAO();
        if(validateEmailForUpdate(user.getEmailId(), user.getUserId()))
            return EMAIL_EXISTS;
        if(validateUserNameForUpdate(user.getUserName(), user.getUserId()))
            return USER_NAME_EXISTS;
        if(userDAO.updatedUser(user))
            return USER_UPDATED;
        return SERVER_SIDE_PROBLEM_TRY_AGAIN_LATER;
    }

    public boolean validateEmailForUpdate(String email,Long id) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserDetailsByEmail(email);
        if(user == null)
            return false;
        return !user.getUserId().equals(id);
    }

    public boolean validateUserNameForUpdate(String userName,Long id) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserDetailsByUserName(userName);
        if(user == null)
            return false;
        return !user.getUserId().equals(id);
    }

    public void updatePermissions(Permissions permissions, Long userId, String creatorUser) {
        UserDAO userDAO = new UserDAO();
        userDAO.updatePermissions(userId,1,permissions.getDashboardAdd(),permissions.getDashboardDelete(),
                permissions.getDashboardModify(),permissions.getDashboardRead(),creatorUser);
        userDAO.updatePermissions(userId,2,permissions.getSettingsAdd(),permissions.getSettingsDelete(),
                permissions.getSettingsModify(),permissions.getSettingsRead(),creatorUser);
        userDAO.updatePermissions(userId,3,permissions.getUserInformationAdd(),permissions.getUserInformationDelete(),
                permissions.getUserInformationModify(),permissions.getUserInformationRead(),creatorUser);
        userDAO.updatePermissions(userId,4,permissions.getWebpage1Add(),permissions.getWebpage1Delete(),
                permissions.getWebpage1Modify(),permissions.getWebpage1Read(),creatorUser);
        userDAO.updatePermissions(userId,5,permissions.getWebpage2Add(),permissions.getWebpage2Delete(),
                permissions.getWebpage2Modify(),permissions.getWebpage2Read(),creatorUser);
        userDAO.updatePermissions(userId,6,permissions.getWebpage3Add(),permissions.getWebpage3Delete(),
                permissions.getWebpage3Modify(),permissions.getWebpage3Read(),creatorUser);
    }

    public void deleteUser(Long userId) {
        UserDAO userDAO = new UserDAO();
        userDAO.deletePermissions(userId);
        userDAO.deleteUserDetails(userId);
    }

    public Messages updateImage(Long userId, User updatedUser) {
        if(new UserDAO().updateImage(userId,updatedUser))
            return IMAGE_UPDATED;
        return SERVER_SIDE_PROBLEM_TRY_AGAIN_LATER;
    }

    public Long getNumberOfUsers() {
        return new UserDAO().getNumberOfUsers();
    }

    public List<User> getLimitedUsers(int pageId, int numberOfUsersToDisplay) {
        int startNumber = (pageId-1)*numberOfUsersToDisplay;
        return new UserDAO().getLimitedUsers(startNumber,numberOfUsersToDisplay);
    }

    public Long getNumberOfUsers(String searchWord) {
        return new UserDAO().getNumberOfUsers("%"+searchWord+"%");
    }

    public List<User> getLimitedUsers(Integer pageId, int numberOfUsersToDisplay, String searchWord) {
        int startNumber = (pageId-1)*numberOfUsersToDisplay;
        return new UserDAO().getLimitedUsers(startNumber,numberOfUsersToDisplay,"%"+searchWord+"%");
    }

    public Long getNumberOfUsersByStatus(String status) {
        return new UserDAO().getNumberOfUsersByStatus(status);
    }

    public Double getFemaleRatio(int userChoice) {
        Long numberOfFemaleUsers = new UserDAO().getNumberOfUsersByGender("Female",userChoice);
        Long numberOfUsers = getNumberOfUsers(userChoice);
        if(numberOfFemaleUsers != null & numberOfUsers != 0)
            return Double.parseDouble(decimalFormat.format(numberOfFemaleUsers*100.0/numberOfUsers));
        return null;
    }

    private Long getNumberOfUsers(int userChoice) {
        return new UserDAO().getNumberOfUsers(userChoice);
    }

    public Double getMaleRatio(int userChoice) {
        if(getFemaleRatio(userChoice) != null)
            return Double.parseDouble(decimalFormat.format(100 - getFemaleRatio(userChoice)));
        return null;
    }

    public List<User> getRecentRegistrations(int numberOfUsers) {
        return new UserDAO().getRecentRegistrations(numberOfUsers);
    }

    public List<Country> getCountriesWithMaximumUsers(int pageId, int numberOfUsers, int userChoice) {
        int startNumber = (pageId-1)*numberOfUsers;
        return new UserDAO().getCountriesWithMaximumUsers(startNumber,numberOfUsers, userChoice);
    }

    public Map<String,Integer> getNumberOfUsersByAge() {
        return new UserDAO().getNumberOfUsersByAge();
    }

    public void setUserLogin(Long userId) {
        UserDAO userDAO = new UserDAO();
        if(!userDAO.setUserLogin(userId))
            userDAO.insertUserLoginDetails(userId);
    }

    public void setUserLogout(Long userId) {
        new UserDAO().setUserLogout(userId);
    }

    public Long getNumberOfUsersOnline() {
        return new UserDAO().getNumberOfUsersOnline();
    }

    public Map<String, Long> getAllTimeRegisteredUsers() {
        return new UserDAO().getAllTimeRegisteredUsers();
    }

    public Map<String, Long> getRegisteredUsersInCurrentYear() {
        return new UserDAO().getRegisteredUsersInCurrentYear();
    }

    public Map<String, Long> getAllTimeRegisteredUsersInCurrentMonth() {
        return new UserDAO().getRegisteredUsersInCurrentMonth();
    }

    public Map<String,Integer> getNumberOfUsersByAgeInCurrentYear() {
        return new UserDAO().getNumberOfUsersByAgeInCurrentYear();
    }

    public Map<String,Integer> getNumberOfUsersByAgeCurrentMonth() {
        return new UserDAO().getNumberOfUsersByAgeInCurrentMonth();
    }

    public List<Country> getCountriesWithMaximumUsers(int pageId, int numberOfCountries, int userChoice, String searchWord) {
        int startNumber = (pageId-1)*numberOfCountries;
        return new UserDAO().getCountriesWithMaximumUsers(startNumber,numberOfCountries, userChoice,"%"+searchWord+"%");
    }

    public Long getNumberOfCountries(int userChoice) {
        return new UserDAO().getNumberOfCountries(userChoice);
    }

    public Long getCountriesWithMaximumUsers(String searchWord, int userChoice) {
        return new UserDAO().getNumberOfCountries(userChoice,"%"+searchWord+"%");
    }

    public User getUserDetails(String userName, String password) {
        return new UserDAO().getUserDetails(userName,password);
    }

    public Messages validateUserName(String userName) {
        User user = new UserDAO().getUserDetailsByUserName(userName);
        if(user == null)
            return USER_NAME_DOESNT_EXIST;
        return USER_NAME_AND_PASSWORD_DOESNOT_MATCH;
    }
}
