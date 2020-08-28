<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/webpage.css">
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
%>
<div class="webpage">
    <%@include file = "header_navbar.jsp" %>
    <div class="webpage-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="webpage-main" id="main">
            <header class="webpage-header">
                <div class="webpage-header-left">
                    <span class="webpage-header-left-welcome">Webpage 1</span>
                    <span>Permissions granted for webpage 1</span>
                </div>
                <div class="webpage-header-right">
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / Dashboard </span>
                </div>
            </header>
            <div class="webpage-main-content">
                <div class="webpage-main-content-header">
                    Webpage Permissions
                </div>
                <div class="webpage-main-content-details">
                    <div class="webpage-main-content-permission">
                        <label>Add Permission</label>
                        <input type="checkbox" checked disabled>
                    </div>
                    <div class="webpage-main-content-permission">
                        <label>Delete Permission</label>
                        <input type="checkbox" checked disabled>
                    </div>
                    <div class="webpage-main-content-permission">
                        <label>Modify Permission</label>
                        <input type="checkbox" checked disabled>
                    </div>
                    <div class="webpage-main-content-permission">
                        <label>Read Permission</label>
                        <input type="checkbox" checked disabled>
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