<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title><meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Dropify/0.2.2/css/dropify.min.css">
    <link rel="stylesheet" href="css/new_user.css">
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

    if (session.getAttribute("dashboardPermissions")==null) {
        response.sendRedirect("page_not_found");
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
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / New User </span>
                </div>
            </header>
            <form class="new-user-form" action="NewUser" method="post">
                <div class="new-user-information-profile-image">
                    <div class="new-user-information-with-header">
                        <div class="new-user-information-header">General
                            <c:if test = "${not empty message}">
                                <p class="new-user-message"> ${message} </p>
                            </c:if>
                        </div>
                        <div class="new-user-information">
                            <div class="new-user-details">
                                <span>First Name</span>
                                <input  placeholder="First Name" type="text" required pattern="^[A-Z][a-z]{2,}"
                                        title="Starting letter must be upper case and followed by lowercase letters with minimum of 3 characters"
                                        name="firstName" class="new-user-input" >
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
                                <input placeholder="Date Of Birth" type="date" required name="dateOfBirth" class="new-user-input" id="dateId">
                            </div>
                            <div class="new-user-details">
                                <span>Gender</span>
                                <select required name="gender" class="new-user-input">
                                    <option value disabled selected>-- Select --</option>
                                    <option>Female</option>
                                    <option>Male</option>
                                    <option>Others</option>
                                </select>
                            </div>
                            <div class="new-user-details">
                                <span>Country</span>
                                <select required name="country" class="new-user-input">
                                    <option value disabled selected>-- Select --</option>
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
                                <input type="text" placeholder="Mobile Number" required name="mobileNumber" pattern="[0-9]{5,10}"
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
                                <input required pattern=".{5,}" title="Name must contain minimum of 5 characters"
                                       placeholder="User Name" type="text" name="userName" class="new-user-input">
                            </div>
                            <div class="new-user-details">
                                <span>Password</span>
                                <input required name="password" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{8,}"
                                       title="Password must contain atleast one capital letter, special character and number with minimum of 8 characters"
                                       class="new-user-input" type="password" placeholder="Password" id="password">
                            </div>
                            <div class="new-user-details">
                                <span>Confirm Password</span>
                                <input required name="confirmPassword" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{8,}"
                                       title="Password must contain atleast one capital letter, special character and number with minimum of 8 characters"
                                       class="new-user-input" type="password" placeholder="Password" id="confirmPassword">
                            </div>
                            <div class="new-user-details">
                                <span>User Role</span>
                                <select name="userRole" class="new-user-input" id="new-user-role" required onchange="setPermissions()">
                                    <option value disabled selected>-- Select --</option>
                                    <option>User</option>
                                    <option>Admin</option>
                                </select>
                            </div>
                        </div>
                        <div id="password-message"></div>
                    </div>
                    <div class="new-user-profile">
                        <div class="new-user-profile-header">Photo</div>
                        <div class="new-user-profile-details">
                            <label class="new-user-profile-label">Acceptable image formats are jpg, jpeg, png &amp; gif.</label>
                            <label class="new-user-profile-label">Maximum image size allowed is 2MB.</label>
                            <input data-show-loader="true" data-show-remove="true" data-allowed-file-extensions="png jpeg jpg gif"
                                   data-errors-position="inside" type="file" class="dropify" data-max-file-size="2M">
                        </div>
                    </div>
                </div>
                <div class="new-user-permission">
                    <div class="new-user-permission-header">Permissions</div>
                    <div class="new-user-permission-table-div  table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Web Page</th>
                                <th>
                                    <input type="checkbox" name="webpageAdd" class="permission-table-input permission-add"
                                           value="1" onchange="toggleCheckBoxes(this)">
                                    <input type="hidden" name="webpageAdd" class="permission-table-input" value="0">
                                    <span>Add</span>
                                </th>
                                <th>
                                    <input type="checkbox" name="webpageDelete" class="permission-table-input permission-delete"
                                           value="1" onchange="toggleCheckBoxes(this)">
                                    <input type="hidden" name="webpageDelete" class="permission-table-input" value="0">
                                    <span>Delete</span>
                                </th>
                                <th>
                                    <input type="checkbox" name="webpageModify" class="permission-table-input permission-modify"
                                           value="1" onchange="toggleCheckBoxes(this)">
                                    <span>Modify</span>
                                </th>
                                <th>
                                    <input type="checkbox" name="webpageRead" class="permission-table-input permission-read"
                                           value="1" onchange="toggleCheckBoxes(this)">
                                    <span>Read</span>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Dashboard</td>
                                <td><input type="checkbox" name="dashboard-add" class="permission-table-input permission-add" value="1">
                                    <input type="hidden" name="dashboard-add" class="permission-table-input" value="0">
                                </td>
                                <td><input type="checkbox" name="dashboard-delete permission-delete" class="permission-table-input permission-delete" value="1">
                                    <input type="hidden" name="dashboard-delete" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="dashboard-modify" class="permission-table-input permission-modify" value="1">
                                    <input type="hidden" name="dashboard-modify" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="dashboard-read" class="permission-table-input permission-read" value="1">
                                    <input type="hidden" name="dashboard-read" class="permission-table-input" value="0"></td>
                            </tr>
                            <tr>
                                <td>Settings</td>
                                <td>
                                    <input type="checkbox" name="settings-add" class="permission-table-input permission-add" value="1">
                                    <input type="hidden" name="settings-add" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="settings-delete permission-delete" class="permission-table-input permission-delete" value="1">
                                    <input type="hidden" name="settings-delete" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="settings-modify" class="permission-table-input permission-modify" value="1">
                                    <input type="hidden" name="settings-modify" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="settings-read" class="permission-table-input permission-read" value="1">
                                    <input type="hidden" name="settings-read" class="permission-table-input" value="0"></td>
                            </tr>
                            <tr>
                                <td>Users Information</td>
                                <td><input type="checkbox" name="userinfo-add" class="permission-table-input permission-add" value="1">
                                    <input type="hidden" name="userinfo-add" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="userinfo-delete" class="permission-table-input permission-delete" value="1">
                                    <input type="hidden" name="userinfo-delete" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="userinfo-modify" class="permission-table-input permission-modify permission-user" value="1">
                                    <input type="hidden" name="userinfo-modify" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="userinfo-read" class="permission-table-input permission-read" value="1">
                                    <input type="hidden" name="userinfo-read" class="permission-table-input" value="0"></td>
                            </tr>
                            <tr>
                                <td>Web Page 1</td>
                                <td><input type="checkbox" name="webpage1-add" class="permission-table-input permission-add  permission-user" value="1">
                                    <input type="hidden" name="webpage1-add" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="webpage1-delete" class="permission-table-input permission-delete" value="1">
                                    <input type="hidden" name="webpage1-delete" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="webpage1-modify" class="permission-table-input permission-modify permission-user" value="1">
                                    <input type="hidden" name="webpage1-modify" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="webpage1-read" class="permission-table-input permission-read permission-user" value="1">
                                    <input type="hidden" name="webpage1-read" class="permission-table-input" value="0"></td>
                            </tr>
                            <tr>
                                <td>Web Page 2</td>
                                <td><input type="checkbox" name="webpage2-add" class="permission-table-input permission-add permission-user" value="1">
                                    <input type="hidden" name="webpage2-add" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="webpage2-delete" class="permission-table-input permission-delete" value="1">
                                    <input type="hidden" name="webpage2-delete" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="webpage2-modify" class="permission-table-input permission-modify permission-user" value="1">
                                    <input type="hidden" name="webpage2-modify" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="webpage2-read" class="permission-table-input permission-read  permission-user" value="1">
                                    <input type="hidden" name="webpage2-read" class="permission-table-input" value="0"></td>
                            </tr>
                            <tr>
                                <td>Web Page 3</td>
                                <td><input type="checkbox" name="webpage3-add" class="permission-table-input permission-add permission-user" value="1">
                                    <input type="hidden" name="webpage3-add" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="webpage3-delete" class="permission-table-input permission-delete" value="1">
                                    <input type="hidden" name="webpage3-delete" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="webpage3-modify" class="permission-table-input permission-modify permission-user" value="1">
                                    <input type="hidden" name="webpage3-modify" class="permission-table-input" value="0"></td>
                                <td><input type="checkbox" name="webpage3-read" class="permission-table-input permission-read permission-user" value="1">
                                    <input type="hidden" name="webpage3-read" class="permission-table-input" value="0"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="new-user-details-submit-or-reset">
                    <button class="new-user-submit-button btn btn-primary" onclick="return checkPassword()">Submit</button>
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
<%
    session.setAttribute("message",null);
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Dropify/0.2.2/js/dropify.min.js"></script>
<script src="script/dashboard.js"></script>
</body>
</html>