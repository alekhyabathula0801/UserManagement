<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/dashboard.css">
    <link rel="stylesheet" href="css/header_navbar.css">
    <link rel="stylesheet" href="css/side_tool_bar.css">
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if (session.getAttribute("user")==null) {
        session.setAttribute("message","Please Login");
        response.sendRedirect("login");
    }

    if (session.getAttribute("dashboardPermissions")==null) {
        response.sendRedirect("page_not_found");
    }
%>
<div class="dashboard">
    <%@include file = "header_navbar.jsp" %>
    <div class="dashboard-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="dashboard-main" id="main">
            <header class="dashboard-header">
                <div class="dashboard-header-left">
                    <span class="dashboard-header-left-welcome">Welcome ${user.getUserName()}</span>
                    <span>You last logged in on: Aug 25 2020 6:04AM</span>
                </div>
                <div class="dashboard-header-right">
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / Dashboard </span>
                </div>
            </header>
            <div class="dashboard-main-content">
                <div class="dashboard-main-users-status-info">
                    <div class="dashboard-main-users-widget">
                        <span class="dashboard-main-icon-wrapper dashboard-main-total-icon-wrapper">
                            <i class="ti-user"></i>
                        </span>
                        <span class="dashboard-main-content-status-info-right">
                            <span class="dashboard-main-content-title">Total</span>
                            <span class="dashboard-main-content-value">${numberOfUsers}</span>
                        </span>
                    </div>
                    <div class="dashboard-main-users-widget">
                        <span class="dashboard-main-icon-wrapper dashboard-main-active-icon-wrapper">
                            <i class="ti-check"></i>
                        </span>
                        <span class="dashboard-main-content-status-info-right">
                            <span class="dashboard-main-content-title">Active</span>
                            <span class="dashboard-main-content-value">${numberOfActiveUsers}</span>
                        </span>
                    </div>
                    <div class="dashboard-main-users-widget">
                        <span class="dashboard-main-icon-wrapper dashboard-main-inactive-icon-wrapper">
                            <i class="ti-na"></i>
                        </span>
                        <span class="dashboard-main-content-status-info-right">
                            <span class="dashboard-main-content-title">Inactive</span>
                            <span class="dashboard-main-content-value">${numberOfInactiveUsers}</span>
                        </span>
                    </div>
                    <div class="dashboard-main-users-widget">
                        <span class="dashboard-main-icon-wrapper dashboard-main-online-icon-wrapper">
                            <i class="ti-eye"></i>
                        </span>
                        <span class="dashboard-main-content-status-info-right">
                            <span class="dashboard-main-content-title">Online</span>
                            <span class="dashboard-main-content-value">16</span>
                        </span>
                    </div>
                </div>
                <div class="dashboard-main-registration-details">
                    <div class="dashboard-main-all-time-registration">
                        <header class="dashboard-main-all-time-registration-header">
                            All Time Registration History
                        </header>
                        <div class="dashboard-main-all-time-registration-main">
                            <div class="dashboard-main-registration-graph">
                                <div class="dashboard-main-registration-graph-header">
                                    <button class="dashboard-main-registration-graph-options">All Time</button>
                                    <button class="dashboard-main-registration-graph-options">2020</button>
                                    <button class="dashboard-main-registration-graph-options">September</button>
                                </div>
                                <div>All time Graph</div>
                            </div>
                            <div class="dashboard-main-all-time-user-details">
                                <div class="dashboard-main-users-in-top-location">
                                    <h3 class="dashboard-main-users-top-location-header">Top Locations</h3>
                                    <div class="table-responsive">
                                        <table class="table table-borderless table-hover">
                                            <tbody>
                                            <tr><td>1</td><td>India</td><td>15</td></tr>
                                            <tr><td>2</td><td>Iraq</td><td>5</td></tr>
                                            <tr><td>3</td><td>Egypt</td><td>3</td></tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="dashboard-main-users-gender-ratio">
                                    <h3 class="dashboard-main-users-gender-ratio-header">Gender</h3>
                                    <div class="dashboard-main-users-gender-ratio-content">
                                        <div class="dashboard-main-users-gender-ratio-details">
                                            <span class="dashboard-main-users-gender-title">Male</span>
                                            <span class="dashboard-main-users-gender-value">30%</span>
                                        </div>
                                        <div class="progress">
                                            <div class="progress-bar" role="progressbar" style="width:30%">
                                                <span class="sr-only"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="dashboard-main-users-gender-ratio-content">
                                        <div class="dashboard-main-users-gender-ratio-details">
                                            <span class="dashboard-main-users-gender-title">Female</span>
                                            <span class="dashboard-main-users-gender-value">70%</span>
                                        </div>
                                        <div class="progress">
                                            <div class="progress-bar" role="progressbar" style="width:70%">
                                                <span class="sr-only"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="dashboard-main-users-age-graph">
                                    <h3 class="dashboard-main-users-age-graph-header">Age Group</h3>
                                    <div>
                                        Age chart
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="dashboard-main-latest-registration">
                        <header class="dashboard-main-latest-registration-header">
                            <span class="dashboard-main-latest-registration-tittle">Latest Registration</span>
                            <a href="#" class="dashboard-main-latest-registration-load">Load More</a>
                        </header>
                        <div class="dashboard-main-latest-registration-main">
                            <div class="dashboard-main-latest-registration-details table-responsive">
                                <table class="table table-hover">
                                    <tbody>
                                    <tr>
                                        <td class="dashboard-main-latest-registration-user-image">
                                            <img src="data:image/jpg;base64, ${user.getUserImage()} ">
                                        </td>
                                        <td class="dashboard-main-latest-registration-user-details">
                                            <a href="#">Alekhya Bathula</a>
                                            <span>Aug 23 2020 3:20PM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="dashboard-main-latest-registration-user-image">
                                            <img src="data:image/jpg;base64, ${user.getUserImage()} ">
                                        </td>
                                        <td class="dashboard-main-latest-registration-user-details">
                                            <a href="#">Arun Kumar</a>
                                            <span>Aug 22 2020 3:20PM</span>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="script/dashboard.js"></script>
</body>
</html>
