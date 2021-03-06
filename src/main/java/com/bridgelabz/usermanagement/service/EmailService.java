package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.model.User;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class EmailService {

    String host = null;
    String port = null;
    String senderEmail = null;
    String password = null;
    String senderName = null;
    final static Logger logger = Logger.getLogger(UserManagementService.class);
    public boolean sendMail(String email) {
        User user = new UserDAO().getUserDetailsByEmail(email);
        logger.info("user details with email "+email+" are "+user);
        if(user == null)
            return false;

        setEmailProperties();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        };

        try {
            Session session = Session.getInstance(properties, auth);

            Message message = new MimeMessage(session);

            String subject = "Password Recovery Request";

            message.setFrom(new InternetAddress(senderEmail, senderName));
            InternetAddress[] toAddresses = { new InternetAddress(user.getEmailId()) };
            message.setRecipients(Message.RecipientType.TO, toAddresses);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(getEmailContent(user.getUserFullName(), user.getUserName(), user.getEmailId(), user.getPassword()));
            Transport.send(message);
            logger.info("recovery password mail sent");
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return false;
    }

    public void setEmailProperties() {
        try {
            Properties emailProperties = new Properties();
            emailProperties.load(getClass().getClassLoader().getResourceAsStream("email.properties"));
            host = emailProperties.getProperty("host");
            port = emailProperties.getProperty("port");
            senderEmail = emailProperties.getProperty("senderMail");
            password = emailProperties.getProperty("password");
            senderName = emailProperties.getProperty("senderName");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
    }

    public String getEmailContent(String userFullName, String userName, String mail, String password) {
        return "Welcome " + userFullName +
                "\n\nWe received an password recovery request on User Management for " + mail +
                "\n\nYour User Name is " + userName +
                "\n\nYour Password is " + password +
                "\n\nYou didn't request your password?" +
                "\nAnyone can request this information, but only you will receive this email. This is done so that you" +
                " can access your information from anywhere, using any computer. If you received this email but did" +
                " not yourself request the information, then rest assured that the person making the request did not" +
                " gain access to any of your information."+
                "\n\nRegards,"+
                "\n" + senderName + " - User Management";
    }

}
