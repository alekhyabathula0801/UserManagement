<%--
  Created by IntelliJ IDEA.
  User: arun kumar
  Date: 18-08-2020
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="login">
    <div class="login-left">
        <form action="Login" method="post">
            <div class="login-user-management-image">
                <img class="img-circle img-thumbnail img-responsive" src="http://www.ittutorials.in/images/mi-logo.jpg"
                     alt="User Management">
            </div>
            <p class="login-info">
                Login to your account
            </p>
            <div><input type="email" placeholder="Username" name="userName"></div>
            <div><input type="password" placeholder="Password" name="password"></div>
            <div><button id="login-button">Login</button></div>
        </form>
    </div>
    <div class="login-right"></div>
</div>
</body>
</html>
