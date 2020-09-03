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
                            <span class="dashboard-main-content-value">161</span>
                        </span>
                    </div>
                    <div class="dashboard-main-users-widget">
                        <span class="dashboard-main-icon-wrapper dashboard-main-active-icon-wrapper">
                            <i class="ti-check"></i>
                        </span>
                        <span class="dashboard-main-content-status-info-right">
                            <span class="dashboard-main-content-title">Active</span>
                            <span class="dashboard-main-content-value">160</span>
                        </span>
                    </div>
                    <div class="dashboard-main-users-widget">
                        <span class="dashboard-main-icon-wrapper dashboard-main-inactive-icon-wrapper">
                            <i class="ti-na"></i>
                        </span>
                        <span class="dashboard-main-content-status-info-right">
                            <span class="dashboard-main-content-title">Inactive</span>
                            <span class="dashboard-main-content-value">1</span>
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
