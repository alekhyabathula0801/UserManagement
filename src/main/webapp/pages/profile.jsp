<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/profile.css">
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
<div class="profile">
    <%@include file = "header_navbar.jsp" %>
    <div class="profile-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="profile-main" id="main">
            <header class="profile-header">
                <div class="profile-header-left">
                    <span class="profile-header-left-welcome">Profile</span>
                    <span>You last logged in on: Sep 17 2020 8:47AM</span>
                </div>
                <div class="profile-header-right">
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / Profile </span>
                </div>
            </header>
            <div class="profile-main-content">
                <div class="profile-main-content-basic">
                    <header class="profile-main-basic-header">
                        <div class="profile-main-basic-header-overlay">
                            <img alt="user image" src="data:image/jpg;base64, ${user.getUserImage()} ">
                            <span class="profile-main-basic-header-user-name">Alekhya</span>
                        </div>
                    </header>
                    <main class="profile-main-content-basic-main">
                        <header>Basic Info</header>
                        <div class="table-responsive">
                            <table class="table table-borderless">
                                <tbody>
                                <tr>
                                    <td>Email</td>
                                    <td>alekhya@gmail.com</td>
                                </tr>
                                <tr>
                                    <td>Username</td>
                                    <td>Alekhya</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <a href="#" class="btn btn-primary profile-main-content-baisc-edit">Edit Profile</a>
                    </main>
                </div>
                <div class="profile-main-content-general">

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
