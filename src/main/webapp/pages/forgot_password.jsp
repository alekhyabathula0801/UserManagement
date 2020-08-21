<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" href="css/forgot_password.css">
</head>
<body>
<div class="forgot-password">
    <form class="forgot-password-form">
        <div class="forgot-password-header">
            <img class="img-circle img-thumbnail img-responsive" src="http://www.ittutorials.in/images/mi-logo.jpg"
                 alt="User Management">
            <h3>User Management</h3>
            <p class="forgot-password-recover-text">Recover Password</p>
            <p class="forgot-password-entry-info">Please enter your email address below to receive the password at your
                registered email.</p>
        </div>
        <input type="text" required pattern="^[a-zA-Z0-9]+([._+-][0-9a-zA-Z]+)*@[a-zA-Z0-9]+\.[a-zA-Z]{2,4}([.][a-zA-Z]{2,3})?$"
               title="sample email pattern - char@char.com or char@char.com.in" placeholder="Email"
               name="forgotPasswordEmail" class="forgot-password-email-input">
        <button class="forgot-password-button">RECOVER PASSWORD</button>
        <div class="forgot-password-bottom">
            <span class="helper-text">Know your password? <a href="login.jsp">Login</a></span>
        </div>
    </form>
</div>
</body>
</html>
