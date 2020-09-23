package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.bridgelabz.usermanagement.enumeration.Messages.*;

public class UpdateImage extends HttpServlet {
    final static Logger logger = Logger.getLogger(UpdateImage.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        logger.info("request to update image received with user id : "+userId);
        User updateUser = new User();
        InputStream inputStream = null;
        Part filePart = request.getPart("user-profile-image");
        Messages messages;
        UserManagementService service = new UserManagementService();
        if (filePart.getSize() != 0) {
            inputStream = filePart.getInputStream();
            updateUser.setUserImageInputStream(inputStream);
            User creatorUser = (User) request.getSession().getAttribute("user");
            updateUser.setCreatorUser(creatorUser.getUserName());
            messages = service.updateImage(userId,updateUser);
            logger.info("update image message : "+messages);
        } else {
            logger.warn("request failed. user didn't upload an image");
            messages = PLEASE_UPLOAD_IMAGE_TO_CHANGE;
        }
        request.getSession().setAttribute("imageMessage",service.convertToString(messages));
        RequestDispatcher rd = request.getRequestDispatcher("UserDetails?userId="+userId);
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        logger.info("request to remove user image received");
        User updateUser = new User();
        Messages messages;
        UserManagementService service = new UserManagementService();
        InputStream inputStream = new FileInputStream("C:\\Users\\arun kumar\\IdeaProjects\\UserManagementApp\\src\\main\\webapp\\assests\\default-user-image.png");
        updateUser.setUserImageInputStream(inputStream);
        User creatorUser = (User) request.getSession().getAttribute("user");
        updateUser.setCreatorUser(creatorUser.getUserName());
        messages = service.updateImage(userId,updateUser);
        if(messages.equals(IMAGE_UPDATED))
            messages = DEFAULT_IMAGE_ADDED;
        logger.info("remove image status message : "+messages);
        request.getSession().setAttribute("imageMessage",service.convertToString(messages));
        RequestDispatcher rd = request.getRequestDispatcher("UserDetails?userId="+userId);
        rd.forward(request, response);
    }
}
