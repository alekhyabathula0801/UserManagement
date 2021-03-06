package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.enumeration.Messages;

import java.io.InputStream;

public class User {

    private String userName;
    private String emailId;
    private Long userId;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String countryCode;
    private String country;
    private Long mobileNumber;
    private String gender;
    private String dateOfBirth;
    private String userRole;
    private String address;
    private String creatorUser;
    private InputStream userImageInputStream;
    private String userFullName;
    private String userStatus;
    private String creatorStamp;
    private String lastLoginStamp;
    private String userImage;

    public User() {
    }

    public User(String userName, String emailId, Long userId) {
        this.userName = userName;
        this.emailId = emailId;
        this.userId = userId;
    }

    public String getLastLoginStamp() {
        return lastLoginStamp;
    }

    public void setLastLoginStamp(String lastLoginStamp) {
        this.lastLoginStamp = lastLoginStamp;
    }

    public String getCreatorStamp() {
        return creatorStamp;
    }

    public void setCreatorStamp(String creatorStamp) {
        this.creatorStamp = creatorStamp;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void  setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public InputStream getUserImageInputStream() {
        return userImageInputStream;
    }

    public void setUserImageInputStream(InputStream userImageInputStream) {
        this.userImageInputStream = userImageInputStream;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", userRole='" + userRole + '\'' +
                ", address='" + address + '\'' +
                ", creatorUser='" + creatorUser + '\'' +
                ", userImageInputStream=" + userImageInputStream +
                ", userFullName='" + userFullName + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", creatorStamp='" + creatorStamp + '\'' +
                ", lastLoginStamp='" + lastLoginStamp + '\'' +
                ", userImage='" + userImage + '\'' +
                '}';
    }
}
