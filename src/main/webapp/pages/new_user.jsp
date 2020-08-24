<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title><meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/new_user.css">
    <link rel="stylesheet" href="css/header_navbar.css">
    <link rel="stylesheet" href="css/side_tool_bar.css">
</head>
<body>
<%
    if (session.getAttribute("userName")==null) {
        session.setAttribute("message","Please Login");
        response.sendRedirect("login.jsp");
    }
%>
<div class="new-user">
    <%@include file = "header_navbar.jsp" %>
    <div class="new-user-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="new-user-main" id="main">
            <header class="new-user-header">
                <div class="new-user-header-left">
                    New User
                </div>
                <div class="new-user-header-right">
                    <a href="dashboard.jsp"><i class="fa fa-home"></i>Home</a>
                    <span> / New User </span>
                </div>
            </header>
            <form class="new-user-form">
                <div class="new-user-information-profile-image">
                    <div class="new-user-information-with-header">
                        <div class="new-user-information-header">General</div>
                        <div class="new-user-information">
                            <div class="new-user-details">
                                <span>First Name</span>
                                <input  placeholder="First Name" type="text" required pattern="^[A-Z][a-z]{2,}"
                                        title="Starting letter must be upper case and followed by lowercase letters with minimum of 3 characters"
                                        name="firstName" class="new-user-input">
                            </div>
                            <div class="new-user-details">
                                <span>Middle Name</span>
                                <input  placeholder="Middle Name" type="text" pattern="^[A-Z][a-z]{2,}"
                                        title="Starting letter must be upper case and followed by lowercase letters with minimum of 3 characters"
                                        name="middleName" class="new-user-input">
                            </div>
                            <div class="new-user-details">
                                <span>Last Name</span>
                                <input  placeholder="Last Name" type="text" required pattern="^[A-Z][a-z]{2,}"
                                        title="Starting letter must be upper case and followed by lowercase letters with minimum of 3 characters"
                                        name="lastName" class="new-user-input">
                            </div>
                            <div class="new-user-details">
                                <span>Date Of Birth</span>
                                <input placeholder="Date Of Birth" type="date" required name="dateOfBirth" class="new-user-input">
                            </div>
                            <div class="new-user-details">
                                <span>Gender</span>
                                <select name="gender" class="new-user-input">
                                    <option>Female</option>
                                    <option>Male</option>
                                    <option>Others</option>
                                </select>
                            </div>
                            <div class="new-user-details">
                                <span>Country</span>
                                <select name="country" class="new-user-input">
                                    <option>India</option>
                                    <option>Singapore</option>
                                    <option>Malaysia</option>
                                    <option>Italy</option>
                                    <option>Iraq</option>
                                    <option>Egypt</option>
                                </select>
                            </div>
                            <div class="new-user-details">
                                <span>Country Code</span>
                                <input type="text" placeholder="Country code" required name="countryCode" class="new-user-input">
                            </div>
                            <div class="new-user-details">
                                <span>Mobile Number</span>
                                <input type="number" placeholder="Mobile Number" required name="mobileNumber" pattern="[0-9]{5,10}"
                                       title="Mobile number must contain 5-10 digits" class="new-user-input">
                            </div>
                            <div class="new-user-details">
                                <span>Email</span>
                                <input required pattern="^[a-zA-Z0-9]+([._+-][0-9a-zA-Z]+)*@[a-zA-Z0-9]+\.[a-zA-Z]{2,4}([.][a-zA-Z]{2,3})?$"
                                       title="sample email pattern - char@char.com or char@char.com.in" placeholder="Email ID" type="text"
                                       name="email" class="new-user-input">
                            </div>
                            <div class="new-user-details">
                                <span>Address</span>
                                <textarea placeholder="Address" name="address" class="new-user-input new-user-address"></textarea>
                            </div>
                            <hr>
                            <div class="new-user-details">
                                <span>User Name</span>
                                <input required pattern="{5,}" title="Name must contain minimum of 5 characters"
                                       placeholder="User Name" type="text" name="userName" class="new-user-input">
                            </div>
                            <div class="new-user-details">
                                <span>Password</span>
                                <input required name="password" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{8,}"
                                       title="Password must contain atleast one capital letter, special character and number with minimum of 8 characters"
                                       class="new-user-input" type="password" placeholder="Password" >
                            </div>
                            <div class="new-user-details">
                                <span>Confirm Password</span>
                                <input required name="confirmPassword" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{8,}"
                                       title="Password must contain atleast one capital letter, special character and number with minimum of 8 characters"
                                       class="new-user-input" type="password" placeholder="Password" >
                            </div>
                            <div class="new-user-details">
                                <span>User Role</span>
                                <select name="userRole" class="new-user-input">
                                    <option>User</option>
                                    <option>Admin</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="new-user-profile">
                        <div class="new-user-profile-header">Photo</div>
                        <div class="new-user-profile-details">
                            <label class="new-user-profile-label">Acceptable image formats are jpg, jpeg, png &amp; gif.</label>
                            <label class="new-user-profile-label">Maximum image size allowed is 2MB.</label>
                        </div>
                    </div>
                </div>
                <div class="new-user-permission-table"></div>
                <div class="new-user-details-submit-or-reset">
                    <button class="new-user-submit-button btn btn-primary">Submit</button>
                    <button class="new-user-reset-button btn btn-default">Reset</button>
                </div>
            </form>
            <footer class="new-user-footer">
                <div class="container-fluid">
                    <p class="copyright">
                        ©
                        <script type="text/javascript">document.write(new Date().getFullYear())</script>
                        <a href="#">mimtiyaz - CodeCanyon</a>
                    </p>

                </div>
            </footer>
        </main>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="script/dashboard.js"></script>
</body>
</html>