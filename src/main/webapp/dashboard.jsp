<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
        <div class="dashboard-nav-right dropdown">
            <a class="dashboard-user-name dropdown-toggle" data-toggle="dropdown">${userName}</a>
            <div class="dashboard-nav-right-dropdown-menu dropdown-menu dropdown-menu-right">
                <a class="dropdown-item" href="#"><i class="ti-user"></i>Profile</a>
                <a class="dropdown-item"href="#"><i class="ti-power-off"></i>Logout</a>
            </div>
        </div>
    </nav>
</div>
</body>
</html>
