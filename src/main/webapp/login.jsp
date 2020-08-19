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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="login">
    <div class="login-left">
        <form action="Login" method="post" class="login-form">
            <div class="login-left-header">
                <div class="login-user-management-image">
                    <img class="img-circle img-thumbnail img-responsive" src="http://www.ittutorials.in/images/mi-logo.jpg"
                         alt="User Management">
                </div>
                <p class="login-info">
                    Login to your account
                </p>
            </div>
            <div class="login-input"><input type="text" pattern="^[a-zA-Z0-9]+([._+-][0-9a-zA-Z]+)*@[a-zA-Z0-9]+\.[a-zA-Z]{2,4}([.][a-zA-Z]{2,3})?$"
                        title="sample email pattern - char@char.com or char@char.com.in" placeholder="Username"
                        name="userName" ></div>
            <div class="login-input"><input type="password" placeholder="Password" name="password" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{5,}"
                        title="Password must contain atleast one capital letter, special character and number with minimum of 5 characters"></div>
            <div class="login-input"><button id="login-button">LOGIN</button></div>
            <div class="login-forget-password">
                <i class="fa fa-lock"></i>
                <a href="#">Forgot password?</a>
            </div>
        </form>
    </div>
    <div class="login-right">
        <div class="login-right-content">
            <h1>User Management</h1>
            <p> Version 2.2</p>
        </div>
    </div>
</div>
</body>
</html>
