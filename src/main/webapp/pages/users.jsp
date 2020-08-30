<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/users.css">
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
<div class="users">
    <%@include file = "header_navbar.jsp" %>
    <div class="users-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="users-main" id="main">
            <header class="users-header">
                <div class="users-header-left">
                    <span class="users-header-left-welcome">Users</span>
                </div>
                <div class="users-header-right">
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / Users </span>
                </div>
            </header>
            <div class="users-main-content">
                <div class="users-main-content-header">
                    <a href="new_user" class="users-main-content-anchor">
                        <i class="ti-user users-icon"></i>
                        New User
                    </a>
                </div>
                <div class="users-main-content-search-select">
                    <select class="users-main-content-select">
                        <option>10</option>
                        <option>20</option>
                        <option>50</option>
                        <option>100</option>
                    </select>
                    <input placeholder="Search.." class="users-main-content-search-input" type="text">
                </div>
                <div class="users-main-content-table">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Image</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>DOB</th>
                                <th>Status</th>
                                <th>Role</th>
                                <th>Account</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                        </table>
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