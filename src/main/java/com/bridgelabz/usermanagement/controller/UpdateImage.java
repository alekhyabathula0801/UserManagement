package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.enumeration.Messages;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static com.bridgelabz.usermanagement.enumeration.Messages.PLEASE_UPLOAD_IMAGE_TO_CHANGE;

@WebServlet("/UpdateImage")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));
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
        } else {
            messages = PLEASE_UPLOAD_IMAGE_TO_CHANGE;
        }
        request.getSession().setAttribute("imageMessage",service.convertToString(messages));
        RequestDispatcher rd = request.getRequestDispatcher("UserDetails?userId="+userId);
        rd.forward(request, response);
    }
}
