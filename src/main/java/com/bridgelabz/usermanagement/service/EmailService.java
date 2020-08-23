package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dao.UserDAO;
import com.bridgelabz.usermanagement.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public boolean sendMail(String email) {
        User user = new UserDAO().getUserDetailsByEmail(email);
        if(user==null)
            return false;
        try {
            FileReader reader = new FileReader("C:\\Users\\arun kumar\\IdeaProjects\\UserManagementApp\\src\\main\\resources\\email.properties");
            Properties emailProperties = new Properties();
            emailProperties.load(reader);
            host = emailProperties.getProperty("host");
            port = emailProperties.getProperty("port");
            senderEmail = emailProperties.getProperty("senderMail");
            password = emailProperties.getProperty("password");
            senderName = emailProperties.getProperty("senderName");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            String content = "Welcome " + user.getUserName() + "\n\nWe received an password recovery request on " +
                    "User Management for " + user.getEmailId() +
                    "\nYour password is " + user.getPassword() +
                    "\n\n\nYou didn't request your password?" +
                    "\nNote: for security reason, " +
                    "Anyone can request this information, but only you will receive this email. This is done so that you" +
                    " can access your information from anywhere, using any computer. If you received this email but did" +
                    " not yourself request the information, then rest assured that the person making the request did not" +
                    " gain access to any of your information."+
                    "\nRegards,"+
                    "\n" + senderName + " - User Management";

            String subject = "Password Recovery Request";
            message.setFrom(new InternetAddress(senderEmail, senderName));
            InternetAddress[] toAddresses = { new InternetAddress(user.getEmailId()) };
            message.setRecipients(Message.RecipientType.TO, toAddresses);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(content);

            Transport.send(message);
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

}
