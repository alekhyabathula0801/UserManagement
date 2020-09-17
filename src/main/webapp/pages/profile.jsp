<%@ page import="com.bridgelabz.usermanagement.model.User" %>
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
                    <span>You last logged in on: ${user.getLastLoginStamp()}</span>
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
                            <span class="profile-main-basic-header-user-name">
                                ${userDetails.getFirstName()} ${userDetails.getLastName()}
                            </span>
                        </div>
                    </header>
                    <main class="profile-main-content-basic-main">
                        <header>Basic Info</header>
                        <div class="table-responsive">
                            <table class="table table-borderless">
                                <tbody>
                                <tr>
                                    <td>Email</td>
                                    <td>${userDetails.getEmailId()}</td>
                                </tr>
                                <tr>
                                    <td>Username</td>
                                    <td>${userDetails.getUserName()}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <a href="UserDetails?userId=${user.getUserId()}" class="btn btn-primary profile-main-content-baisc-edit">Edit Profile</a>
                    </main>
                </div>
                <div class="profile-main-content-general">
                    <div class="custom-tabs-line tabs-line-bottom left-aligned">
                        <ul class="nav" role="tablist">
                            <li>
                                <a href="#userGeneralInformation" class="active" role="tab" data-toggle="tab">General Information</a>
                            </li>
                            <li><a href="#userLoginHistory" role="tab" data-toggle="tab">Login History</a></li>
                        </ul>
                    </div>
                    <div class="tab-content">
                        <div class="tab-pane fade show active" id="userGeneralInformation">
                            <div class="table-responsive">
                                <table class="table table-borderless">
                                    <%
                                        User userDetails = (User) session.getAttribute("userDetails");
                                    %>
                                    <tbody>
                                    <tr><td>First Name</td><td>${userDetails.getFirstName()}</td></tr>
                                    <tr><td>Middle Name</td>
                                        <%
                                            if(userDetails.getMiddleName().equals("")) {
                                        %>
                                        <td>-</td>
                                        <%
                                        } else {
                                        %>
                                        <td>${userDetails.getMiddleName()}</td>
                                        <%}%>
                                    </tr>
                                    <tr><td>Last Name</td><td>${userDetails.getLastName()}</td></tr>
                                    <tr><td>Date of Birth</td><td>${userDetails.getDateOfBirth()}</td></tr>
                                    <tr><td>Gender</td><td>${userDetails.getGender()}</td></tr>
                                    <tr><td>Country</td><td>${userDetails.getCountry()}</td></tr>
                                    <tr><td>Phone</td><td>${userDetails.getMobileNumber()}</td></tr>
                                    <tr><td>Phone + Ext</td><td>${userDetails.getCountryCode()}</td></tr>
                                    <tr><td>Address</td>
                                        <%
                                            if(userDetails.getAddress().equals("")) {
                                        %>
                                        <td>-</td>
                                        <%
                                        } else {
                                        %>
                                        <td>${userDetails.getAddress()}</td>
                                        <%}%>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="userLoginHistory">
                            <i class="ti-time"></i>&nbsp;<em>Login history is displayed prior to the last login</em>
                            <div class="table-responsive">
                                <table class="table table-borderless">
                                    <tbody>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 9:52:28 AM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 9:41:17 AM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 9:27:04 AM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 8:59:47 AM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 8:47:33 AM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 8:43:09 AM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 8:09:37 AM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 7:58:10 AM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 7:20:41 AM</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <i class="ti-angle-right"></i>&nbsp;<span>9/17/2020 6:57:27 AM</span>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
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
