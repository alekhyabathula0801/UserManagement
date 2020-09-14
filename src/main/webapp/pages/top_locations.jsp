<%@ page import="com.bridgelabz.usermanagement.model.Country" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/top_locations.css">
    <link rel="stylesheet" href="css/header_navbar.css">
    <link rel="stylesheet" href="css/side_tool_bar.css">
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if (session.getAttribute("user")==null & session.getAttribute("dashboardPermissions")==null) {
        session.setAttribute("message","Please Login");
        response.sendRedirect("login");
    } else if (session.getAttribute("dashboardPermissions")==null) {
        response.sendRedirect("page_not_found");
    }
%>
<div class="top-location">
    <%@include file = "header_navbar.jsp" %>
    <div class="top-location-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="top-location-main" id="main">
            <header class="top-location-header">
                <div class="top-location-header-left">
                    <span class="top-location-header-left-welcome">Top Locations</span>
                </div>
                <div class="top-location-header-right">
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / Top Locations </span>
                </div>
            </header>
            <div class="top-location-main-content">
                <div class="top-location-main-content-header" id="user_list_new_user">
                    Top Locations
                </div>
                <form action="TopLocations" method="get" name="topLocationListAction">
                    <div class="top-location-main-content-search-select">
                        <select class="top-location-main-content-select" id="top-location-main-select-maximum-number-to-diplay"
                                name="numberOfCountries" onchange="javascript:document.topLocationListAction.submit();">
                            <option value="10">10</option>
                            <option value="20">20</option>
                        </select>
                        <input placeholder="Search.." class="top-location-main-content-search-input" name="top-location-search-text"
                               value="${searchWord}" type="text">
                    </div>
                    <input type="hidden" value="${userChoice}" name="userChoice">
                    <div class="top-location-main-content-table">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Country</th>
                                    <th>Users</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    List<Country> countries = (List<Country>) request.getAttribute("countryDetails");
                                    if(countries.size() > 0) {
                                        for (Country country: countries) {
                                %>
                                <tr>
                                    <td>
                                        <%=country.getCountry()%>
                                    </td>
                                    <td>
                                        <%=country.getNumberOfUsers()%>
                                    </td>
                                </tr>
                                <%
                                        }
                                    } else {
                                %>
                                <tr>
                                    <td style="padding: 10px; text-align: center;" colspan="2">No Data Available</td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%
                        Long numberOfCountries = (Long) request.getAttribute("numberOfCountries");
                        int numberOfCountriesToDisplay = (int) request.getAttribute("numberOfCountriesToDisplay");
                        if(countries.size()>0) {
                    %>
                    <div>Showing ${startNumber} - ${endNumber} of ${numberOfCountries} entries</div>
                    <%
                        }
                        for(int i=0; i<=numberOfCountries/numberOfCountriesToDisplay; i++) {
                    %>
                    <button type="submit" class="top-location-main-page-number" name="activePageId" value="<%=i+1 %>">
                        <%=i+1%>
                    </button>
                    <%}%>
                </form>
            </div>
        </main>
    </div>
</div>
<script>
    document.getElementById("top-location-main-select-maximum-number-to-diplay").value = "${numberOfCountriesToDisplay}";
    let pages = document.getElementsByClassName("top-location-main-page-number");
    for(let page of pages) {
        if(page.value === "${topLocationActivePage}") {
            page.className = "top-location-main-active-page-number";
        }
    }
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="script/side_tool_bar.js"></script>
</body>
</html>