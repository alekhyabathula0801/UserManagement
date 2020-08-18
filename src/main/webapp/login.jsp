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
            <div><input type="text" pattern="^[a-zA-Z0-9]+([._+-][0-9a-zA-Z]+)*@[a-zA-Z0-9]+\.[a-zA-Z]{2,4}([.][a-zA-Z]{2,3})?$"
                        title="sample email pattern - char@char.com or char@char.com.in" placeholder="Username"
                        name="userName" ></div>
            <div><input type="password" placeholder="Password" name="password" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{5,}"
                        title="Password must contain atleast one capital letter, special character and number with minimum of 5 characters"></div>
            <div><button id="login-button">Login</button></div>
        </form>
    </div>
    <div class="login-right"></div>
</div>
</body>
</html>
