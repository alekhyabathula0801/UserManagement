<%--
  Created by IntelliJ IDEA.
  User: arun kumar
  Date: 18-08-2020
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <nav class="dashboard-nav">
        <div class="dashboard-nav-left">
            <div class="dashboard-barnd">User Management</div>
            <i class="ti-arrow-circle-right"></i>
        </div>
        <div class="dashboard-nav-right">
            <a class="dashboard-user-name">${userName}</a>
            <div class="dashboard-nav-right-dropdown-menu">
                <a href="#"><i class="ti-user"></i>Profile</a>
                <a href="#"><i class="ti-power-off"></i>Logout</a>
            </div>
        </div>
    </nav>
</div>
</body>
</html>
