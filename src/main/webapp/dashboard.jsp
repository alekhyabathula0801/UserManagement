<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<%
    if (session.getAttribute("userName")==null) {
        session.setAttribute("message","Please Login");
        response.sendRedirect("login.jsp");
    }
%>
<div class="dashboard">
    <nav class="dashboard-nav">
        <div class="dashboard-nav-left">
            <div class="dashboard-brand">User Management</div>
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
