package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.model.Country;
import com.bridgelabz.usermanagement.service.UserManagementService;
import com.google.gson.Gson;
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

@WebServlet("/TopLocations")
public class TopLocations extends HttpServlet {
    final static Logger logger = Logger.getLogger(TopLocations.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userChoice = Integer.parseInt(request.getParameter("userChoice"));
        int numberOfUsers = Integer.parseInt(request.getParameter("numberOfUsers"));
        logger.info("request to get top locations received with user choice "+userChoice+" and number of users to display"+
                numberOfUsers);
        UserManagementService service = new UserManagementService();
        List<Country> countriesWithMaximumUsers = service.getCountriesWithMaximumUsers(1,numberOfUsers,userChoice);
        logger.info("countries with maximum number of users are "+countriesWithMaximumUsers);
        String json = new Gson().toJson(countriesWithMaximumUsers);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long numberOfCountries;
        List<Country> countryDetails;
        int numberOfCountriesToDisplay = Integer.parseInt(request.getParameter("numberOfCountries"));
        String pageNumber = request.getParameter("activePageId");
        String searchWord = request.getParameter("top-location-search-text");
        int userChoice = Integer.parseInt(request.getParameter("userChoice"));
        logger.info("request to get top locations received with user choice : "+userChoice+", number of users to display : "+
                numberOfCountriesToDisplay+", page number : "+pageNumber+", search word : "+searchWord);
        HttpSession session = request.getSession();
        Integer pageId;
        if (pageNumber == null)
            pageId = (Integer) session.getAttribute("topLocationActivePage");
        else
            pageId = Integer.parseInt(pageNumber);
        UserManagementService service = new UserManagementService();
        if(searchWord==null) {
            numberOfCountries = service.getNumberOfCountries(userChoice);
            countryDetails = service.getCountriesWithMaximumUsers(pageId,numberOfCountriesToDisplay,userChoice);
        } else {
            numberOfCountries = service.getCountriesWithMaximumUsers(searchWord,userChoice);
            countryDetails = service.getCountriesWithMaximumUsers(pageId, numberOfCountriesToDisplay, userChoice, searchWord);
        }
        int startNumber = (pageId-1)*numberOfCountriesToDisplay;
        request.setAttribute("searchWord",searchWord);
        request.setAttribute("startNumber",startNumber+1);
        request.setAttribute("endNumber",startNumber+countryDetails.size());
        session.setAttribute("topLocationActivePage",pageId);
        request.setAttribute("userChoice",userChoice);
        request.setAttribute("numberOfCountries",numberOfCountries);
        request.setAttribute("numberOfCountriesToDisplay",numberOfCountriesToDisplay);
        request.setAttribute("countryDetails", countryDetails);
        logger.info("top locations are "+countryDetails);
        logger.info("request is dispatched to top_locations page");
        RequestDispatcher rd = request.getRequestDispatcher("top_locations");
        rd.forward(request, response);
    }
}
