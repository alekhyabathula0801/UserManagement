<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title><meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Dropify/0.2.2/css/dropify.min.css">
    <link rel="stylesheet" href="css/update_user.css">
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
<div class="user">
    <%@include file = "header_navbar.jsp" %>
    <div class="user-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="user-main" id="main">
            <header class="user-header">
                <div class="user-header-left">New User</div>
                <div class="user-header-right">
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / New User </span>
                </div>
            </header>
            <div class="update-user-main">
                <form class="user-form" action="Update" method="post">
                    <div class="user-information-with-header">
                        <div class="user-information-header">General
                            <c:if test = "${not empty message}">
                                <p class="user-message"> ${message} </p>
                            </c:if>
                        </div>
                        <input type="hidden" name="user-id" value="${updateUser.getUserId()}">
                        <div class="user-information">
                            <div class="user-details">
                                <span>First Name</span>
                                <input  placeholder="First Name" type="text" required pattern="^[A-Z][a-z]{2,}"
                                        title="Starting letter must be in uppercase and followed by lowercase letters with minimum of 3 characters"
                                        name="firstName" class="user-input" value=${updateUser.getFirstName()}>
                            </div>
                            <div class="user-details">
                                <span>Middle Name</span>
                                <input  placeholder="Middle Name" type="text" pattern="^[A-Z][a-z]{2,}"
                                        title="Starting letter must be in uppercase and followed by lowercase letters with minimum of 3 characters"
                                        name="middleName" class="user-input" value=${updateUser.getMiddleName()}>
                            </div>
                            <div class="user-details">
                                <span>Last Name</span>
                                <input  placeholder="Last Name" type="text" required pattern="^[A-Z][a-z]{2,}"
                                        title="Starting letter must be in uppercase and followed by lowercase letters with minimum of 3 characters"
                                        name="lastName" class="user-input" value=${updateUser.getLastName()}>
                            </div>
                            <div class="user-details">
                                <span>Date Of Birth</span>
                                <input placeholder="Date Of Birth" type="date" required name="dateOfBirth"
                                       class="user-input" id="dateId" value=${updateUser.getDateOfBirth()}>
                            </div>
                            <div class="user-details">
                                <span>Gender</span>
                                <select required name="gender" class="user-input" id="user-gender-input">
                                    <option value disabled selected>-- Select --</option>
                                    <option value="Female">Female</option>
                                    <option value="Male">Male</option>
                                </select>
                            </div>
                            <div class="user-details">
                                <span>Country</span>
                                <select required name="country" class="user-input" id="user-country-input">
                                    <option value disabled selected>-- Select --</option>
                                    <option value="India">India</option>
                                    <option value="Singapore">Singapore</option>
                                    <option value="Malaysia">Malaysia</option>
                                    <option value="Italy">Italy</option>
                                    <option value="Iraq">Iraq</option>
                                    <option value="Egypt">Egypt</option>
                                </select>
                            </div>
                            <div class="user-details">
                                <span>Country Code</span>
                                <input type="text" placeholder="Country code" required name="countryCode" class="user-input"
                                       value=${updateUser.getCountryCode()}>
                            </div>
                            <div class="user-details">
                                <span>Mobile Number</span>
                                <input type="text" placeholder="Mobile Number" required name="mobileNumber" pattern="[0-9]{5,10}"
                                       title="Mobile number must contain 5-10 digits" class="user-input"
                                       value=${updateUser.getMobileNumber()}>
                            </div>
                            <div class="user-details">
                                <span>Email</span>
                                <input required pattern="^[a-zA-Z0-9]+([._+-][0-9a-zA-Z]+)*@[a-zA-Z0-9]+\.[a-zA-Z]{2,4}([.][a-zA-Z]{2,3})?$"
                                       title="sample email pattern - char@char.com or char@char.com.in" placeholder="Email ID" type="text"
                                       name="email" class="user-input" value=${updateUser.getEmailId()}>
                            </div>
                            <div class="user-details">
                                <span>Address</span>
                                <textarea placeholder="Address" name="address" class="user-input user-address"
                                          id="user-address"></textarea>
                            </div>
                            <hr>
                            <div class="user-details">
                                <span>User Name</span>
                                <input required pattern="[a-zA-z0-9]{5,}" title="User name must contain minimum of 5 characters and no special character"
                                       placeholder="User Name" type="text" name="userName" class="user-input"
                                       value=${updateUser.getUserName()}>
                            </div>
                            <div class="user-details">
                                <span>Password</span>
                                <input required name="password" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{8,}"
                                       title="Password must contain atleast one capital letter, special character and number with minimum of 8 characters"
                                       class="user-input" type="password" placeholder="Password" id="password"
                                       value=${updateUser.getPassword()}>
                            </div>
                            <div class="user-details">
                                <span>Confirm Password</span>
                                <input required name="confirmPassword" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{8,}"
                                       title="Password must contain atleast one capital letter, special character and number with minimum of 8 characters"
                                       class="user-input" type="password" placeholder="Password" id="confirmPassword"
                                       value=${updateUser.getPassword()}>
                            </div>
                            <div class="user-details">
                                <span>User Role</span>
                                <select name="userRole" class="user-input" id="user-role" required onchange="setPermissions()">
                                    <option value disabled selected>-- Select --</option>
                                    <option value="User">User</option>
                                    <option value="Admin">Admin</option>
                                </select>
                            </div>
                        </div>
                        <div id="password-message"></div>
                    </div>
                    <div class="user-permission">
                        <div class="user-permission-header">Permissions</div>
                        <div class="user-permission-table-div  table-responsive">
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
                                    <td>
                                        <input type="checkbox" id="dashboard-add" name="dashboard-add" value="1"
                                               class="permission-table-input permission-add">
                                        <input type="hidden" name="dashboard-add" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="dashboard-delete" name="dashboard-delete"
                                               class="permission-table-input permission-delete" value="1">
                                        <input type="hidden" name="dashboard-delete" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="dashboard-modify" name="dashboard-modify"
                                               class="permission-table-input permission-modify" value="1">
                                        <input type="hidden" name="dashboard-modify" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="dashboard-read" name="dashboard-read"
                                               class="permission-table-input permission-read" value="1">
                                        <input type="hidden" name="dashboard-read" class="permission-table-input" value="0">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Settings</td>
                                    <td>
                                        <input type="checkbox" id="settings-add" name="settings-add"
                                               class="permission-table-input permission-add" value="1">
                                        <input type="hidden" name="settings-add" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="settings-delete" name="settings-delete"
                                               class="permission-table-input permission-delete" value="1">
                                        <input type="hidden" name="settings-delete" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="settings-modify" name="settings-modify"
                                               class="permission-table-input permission-modify" value="1">
                                        <input type="hidden" name="settings-modify" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="settings-read" name="settings-read"
                                               class="permission-table-input permission-read" value="1">
                                        <input type="hidden" name="settings-read" class="permission-table-input" value="0">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Users Information</td>
                                    <td>
                                        <input type="checkbox" id="userinfo-add" name="userinfo-add"
                                               class="permission-table-input permission-add" value="1">
                                        <input type="hidden" name="userinfo-add" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="userinfo-delete" name="userinfo-delete"
                                               class="permission-table-input permission-delete" value="1">
                                        <input type="hidden" name="userinfo-delete" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="userinfo-modify" name="userinfo-modify"
                                               class="permission-table-input permission-modify permission-user" value="1">
                                        <input type="hidden" name="userinfo-modify" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="userinfo-read" name="userinfo-read"
                                               class="permission-table-input permission-read" value="1">
                                        <input type="hidden" name="userinfo-read" class="permission-table-input" value="0">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Web Page 1</td>
                                    <td>
                                        <input type="checkbox" id="webpage1-add" name="webpage1-add"
                                               class="permission-table-input permission-add  permission-user" value="1">
                                        <input type="hidden" name="webpage1-add" class="permission-table-input" value="0">
                                    </td>
                                    <td><input type="checkbox" id="webpage1-delete" name="webpage1-delete"
                                               class="permission-table-input permission-delete" value="1">
                                        <input type="hidden" name="webpage1-delete" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="webpage1-modify" name="webpage1-modify"
                                               class="permission-table-input permission-modify permission-user" value="1">
                                        <input type="hidden" name="webpage1-modify" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="webpage1-read" name="webpage1-read"
                                               class="permission-table-input permission-read permission-user" value="1">
                                        <input type="hidden" name="webpage1-read" class="permission-table-input" value="0">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Web Page 2</td>
                                    <td>
                                        <input type="checkbox" id="webpage2-add" name="webpage2-add"
                                               class="permission-table-input permission-add permission-user" value="1">
                                        <input type="hidden" name="webpage2-add" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="webpage2-delete" name="webpage2-delete"
                                               class="permission-table-input permission-delete" value="1">
                                        <input type="hidden" name="webpage2-delete" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="webpage2-modify" name="webpage2-modify"
                                               class="permission-table-input permission-modify permission-user" value="1">
                                        <input type="hidden" name="webpage2-modify" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="webpage2-read" name="webpage2-read"
                                               class="permission-table-input permission-read  permission-user" value="1">
                                        <input type="hidden" name="webpage2-read" class="permission-table-input" value="0">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Web Page 3</td>
                                    <td>
                                        <input type="checkbox" id="webpage3-add" name="webpage3-add"
                                               class="permission-table-input permission-add permission-user" value="1">
                                        <input type="hidden" name="webpage3-add" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="webpage3-delete" name="webpage3-delete"
                                               class="permission-table-input permission-delete" value="1">
                                        <input type="hidden" name="webpage3-delete" class="permission-table-input" value="0">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="webpage3-modify" name="webpage3-modify"
                                               class="permission-table-input permission-modify permission-user" value="1">
                                        <input type="hidden" name="webpage3-modify" class="permission-table-input" value="0">
                                    </td>
                                    <td><input type="checkbox" id="webpage3-read" name="webpage3-read"
                                               class="permission-table-input permission-read permission-user" value="1">
                                        <input type="hidden" name="webpage3-read" class="permission-table-input" value="0">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="user-details-submit-or-reset">
                        <button class="user-submit-button btn btn-primary" onclick="return checkPassword()">Update</button>
                        <button class="user-reset-button btn btn-default">Reset</button>
                    </div>
                </form>
                <div class="user-profile">
                    <div class="user-profile-header">Photo</div>
                    <c:if test = "${not empty imageMessage}">
                        <p class="user-message"> ${imageMessage} </p>
                    </c:if>
                    <div class="update-user-image" id="update-user-image">
                        <div class="update-user-image-container">
                            <img alt="user image" src="data:image/jpg;base64, ${updateUserImage} ">
                        </div>
                        <div class="update-image-action-button">
                            <button class="update-user-image-change" onclick="showDropify()">Change Photo</button>
                        </div>
                    </div>
                    <form action="UpdateImage" method="post" enctype="multipart/form-data"
                          class="user-profile-details" id="update-user-image-input-container" style="display: none">
                            <input type="hidden" name="userId" value="${updateUser.getUserId()}">
                            <div class="user-profile-image-details">
                                <label class="user-profile-label">Acceptable image formats are jpg, jpeg, png &amp; gif.</label>
                                <label class="user-profile-label">Maximum image size allowed is 2MB.</label>
                                <input type="file" class="dropify" data-show-loader="true" data-show-remove="true"
                                       data-allowed-file-extensions="png jpeg jpg gif" data-errors-position="inside"
                                       data-max-file-size="2M" name="user-profile-image">
                            </div>
                            <button class="update-user-input-change">Change Photo</button>
                        </form>
                </div>
            </div>
            <footer class="user-footer">
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
    session.setAttribute("imageMessage",null);
%>

<script>
    document.getElementById("user-country-input").value = "${updateUser.getCountry()}";
    document.getElementById("user-gender-input").value = "${updateUser.getGender()}";
    document.getElementById("user-role").value = "${updateUser.getUserRole()}";
    document.getElementById("user-address").innerText = "${updateUser.getAddress()}";
    if(${permissions.getDashboardAdd()})
        document.getElementById("dashboard-add").checked = true;
    if(${permissions.getDashboardDelete()})
        document.getElementById("dashboard-delete").checked = true;
    if(${permissions.getDashboardModify()})
        document.getElementById("dashboard-modify").checked = true;
    if(${permissions.getDashboardRead()})
        document.getElementById("dashboard-read").checked = true;
    if(${permissions.getSettingsAdd()})
        document.getElementById("settings-add").checked = true;
    if(${permissions.getSettingsDelete()})
        document.getElementById("settings-delete").checked = true;
    if(${permissions.getSettingsModify()})
        document.getElementById("settings-modify").checked = true;
    if(${permissions.getSettingsRead()})
        document.getElementById("settings-read").checked = true;
    if(${permissions.getUserInformationAdd()})
        document.getElementById("userinfo-add").checked = true;
    if(${permissions.getUserInformationDelete()})
        document.getElementById("userinfo-delete").checked = true;
    if(${permissions.getUserInformationModify()})
        document.getElementById("userinfo-modify").checked = true;
    if(${permissions.getUserInformationRead()})
        document.getElementById("userinfo-read").checked = true;
    if(${permissions.getWebpage1Add()})
        document.getElementById("webpage1-add").checked = true;
    if(${permissions.getWebpage1Delete()})
        document.getElementById("webpage1-delete").checked = true;
    if(${permissions.getWebpage1Modify()})
        document.getElementById("webpage1-modify").checked = true;
    if(${permissions.getWebpage1Read()})
        document.getElementById("webpage1-read").checked = true;
    if(${permissions.getWebpage2Add()})
        document.getElementById("webpage2-add").checked = true;
    if(${permissions.getWebpage2Delete()})
        document.getElementById("webpage2-delete").checked = true;
    if(${permissions.getWebpage2Modify()})
        document.getElementById("webpage2-modify").checked = true;
    if(${permissions.getWebpage2Read()})
        document.getElementById("webpage2-read").checked = true;
    if(${permissions.getWebpage3Add()})
        document.getElementById("webpage3-add").checked = true;
    if(${permissions.getWebpage3Delete()})
        document.getElementById("webpage3-delete").checked = true;
    if(${permissions.getWebpage3Modify()})
        document.getElementById("webpage3-modify").checked = true;
    if(${permissions.getWebpage3Read()})
        document.getElementById("webpage3-read").checked = true;
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Dropify/0.2.2/js/dropify.min.js"></script>
<script src="script/side_tool_bar.js"></script>
<script src="script/user.js"></script>
</body>
</html>
