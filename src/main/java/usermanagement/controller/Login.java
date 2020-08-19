package usermanagement.controller;

import usermanagement.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if(new UserDAO().check(userName,password)) {
            session.setAttribute("userName",userName);
            response.sendRedirect("dashboard.jsp");
        } else {
            session.setAttribute("message","Username and Password doesnt match");
            response.sendRedirect("login.jsp");
        }
    }

}
