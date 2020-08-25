<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="login">
    <div class="login-left">
        <form action="Login" method="post" class="login-form">
            <div class="login-left-header">
                <img class="img-circle" src="http://www.ittutorials.in/images/mi-logo.jpg" alt="User Management">
                <p class="login-info">Login to your account</p>
            </div>
            <input required pattern="^[a-zA-Z0-9]+([._+-][0-9a-zA-Z]+)*@[a-zA-Z0-9]+\.[a-zA-Z]{2,4}([.][a-zA-Z]{2,3})?$"
                   title="sample email pattern - char@char.com or char@char.com.in" placeholder="Username" type="text"
                   name="userName" class="login-input">
            <input required placeholder="Password" name="password" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{8,}"
                   title="Password must contain atleast one capital letter, special character and number with minimum of 8 characters"
                   class="login-input" type="password">
            <div class="login-remember-me">
                <label class="switch">
                    <input type="checkbox" checked>
                    <span class="slider round"></span>
                </label>
                <span>Remember Me</span>
            </div>
            <button id="login-button">LOGIN</button>
            <div class="login-forget-password">
                <i class="fa fa-lock"></i>
                <a href="forgot_password">Forgot password?</a>
            </div>
            <c:if test = "${not empty message}">
                <p class="login-message"> ${message} </p>
            </c:if>
        </form>
    </div>
    <div class="login-right">
        <div class="overlay">
            <div class="login-right-content">
                <h1>User Management</h1>
                <p> Version 2.2</p>
            </div>
        </div>
    </div>
</div>
<%
    session.setAttribute("message",null);
%>
</body>
</html>
