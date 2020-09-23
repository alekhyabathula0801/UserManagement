<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/blank_page.css">
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
<div class="blank_page">
    <%@include file = "header_navbar.jsp" %>
    <div class="blank_page-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="blank_page-main" id="main">
            <header class="blank_page-header">
                <div class="blank_page-header-left">
                    <span class="blank_page-header-left-welcome">Blank Page</span>
                    <span>Create your new page based on this starter page.</span>
                </div>
                <div class="blank_page-header-right">
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / Blank Page </span>
                </div>
            </header>
            <div class="blank_page-main-content">
                <div class="blank_page-main-content-header">
                    <span>Page Heading</span>
                    <span class="blank-page-main-title">Page Title</span>
                </div>
                <div class="blank_page-main-content-details">
                   Page content goes here..
                </div>
            </div>
        </main>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="script/side_tool_bar.js"></script>
</body>
</html>
