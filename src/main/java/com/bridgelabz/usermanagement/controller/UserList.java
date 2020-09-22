package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user_list")
public class UserList extends HttpServlet {
    final static Logger logger = Logger.getLogger(UserList.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("request to get user list received");
        UserManagementService service = new UserManagementService();
        Long numberOfUsers = service.getNumberOfUsers();
        List<User> usersDetails = service.getLimitedUsers(1,10);
        logger.info("users details are "+ usersDetails);
        request.setAttribute("startNumber",1);
        request.setAttribute("endNumber",usersDetails.size());
        request.getSession().setAttribute("active_page",1);
        request.setAttribute("numberOfUsersToDisplay",10);
        request.setAttribute("numberOfUsers",numberOfUsers);
        request.setAttribute("usersDetails", usersDetails);
        logger.info("request is dispatched to users_list");
        RequestDispatcher rd = request.getRequestDispatcher("users_list");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        Long numberOfUsers;
        List<User> usersDetails;
        int numberOfUsersToDisplay = Integer.parseInt(request.getParameter("number-of-users"));
        String pageNumber = request.getParameter("active-page-id");
        String searchWord = request.getParameter("user-list-search-text");
        logger.info("request to get user list received with number of users to display : "+numberOfUsersToDisplay+
                ", page number : "+pageNumber+", search word : "+searchWord);
        HttpSession session = request.getSession();
        Integer pageId;
        if (pageNumber == null)
            pageId = (Integer) session.getAttribute("active_page");
        else
            pageId = Integer.parseInt(pageNumber);
        UserManagementService service = new UserManagementService();
        if(searchWord==null) {
            numberOfUsers = service.getNumberOfUsers();
            usersDetails = service.getLimitedUsers(pageId, numberOfUsersToDisplay);
        } else {
            numberOfUsers = service.getNumberOfUsers(searchWord);
            usersDetails = service.getLimitedUsers(pageId, numberOfUsersToDisplay,searchWord);
        }
        logger.info("users details are "+ usersDetails);
        int startNumber = (pageId-1)*numberOfUsersToDisplay;
        request.setAttribute("searchWord",searchWord);
        request.setAttribute("startNumber",startNumber+1);
        request.setAttribute("endNumber",startNumber+usersDetails.size());
        request.getSession().setAttribute("active_page",pageId);
        request.setAttribute("numberOfUsers",numberOfUsers);
        request.setAttribute("numberOfUsersToDisplay",numberOfUsersToDisplay);
        request.setAttribute("usersDetails", usersDetails);
        logger.info("request is dispatched to users_list");
        RequestDispatcher rd = request.getRequestDispatcher("users_list");
        rd.forward(request, response);
    }
}
