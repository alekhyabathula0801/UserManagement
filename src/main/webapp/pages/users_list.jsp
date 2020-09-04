<%@ page import="com.bridgelabz.usermanagement.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/users_list.css">
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
<div class="users">
    <%@include file = "header_navbar.jsp" %>
    <div class="users-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="users-main" id="main">
            <header class="users-header">
                <div class="users-header-left">
                    <span class="users-header-left-welcome">Users</span>
                </div>
                <div class="users-header-right">
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / Users </span>
                </div>
            </header>
            <div class="users-main-content">
                <div class="users-main-content-header" id="user_list_new_user">
                    <a href="new_user" class="users-main-content-anchor">
                        <i class="ti-user users-icon"></i>
                        New User
                    </a>
                </div>
                <form action="user_list" method="post">
                    <div class="users-main-content-search-select">
                        <select class="users-main-content-select" id="users-main-select-maximum-number-to-diplay" name="number-of-users">
                            <option value="10">10</option>
                            <option value="20">20</option>
                        </select>
                        <input placeholder="Search.." class="users-main-content-search-input" type="text">
                    </div>
                    <c:if test = "${not empty message}">
                        <p class="user-message"> ${message} </p>
                    </c:if>
                    <div class="users-main-content-table">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>DOB</th>
                                    <th>Status</th>
                                    <th>Role</th>
                                    <th>Account</th>
                                    <th class="users-action" >Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    List<User> usersDetails = (List<User>) request.getAttribute("usersDetails");
                                    if(usersDetails.size()==0) {
                                %>
                                <tr>
                                    <td class="no-data-message" style="padding: 10px;" colspan="8">No Data Available</td>
                                </tr>
                                <%}else {
                                    int index=1;
                                    for (User user:usersDetails) {
                                        String targetDataId = "#confirm-delete"+index;
                                        String dataId = "confirm-delete"+index;
                                %>
                                <tr>
                                    <td class="users-profile-image">
                                        <img alt="user image" src="data:image/jpg;base64, <%= user.getUserImage()%> "/>
                                    </td>
                                    <td> <%= user.getUserFullName() %> </td>
                                    <td> <%= user.getEmailId() %> </td>
                                    <td> <%= user.getDateOfBirth() %> </td>
                                    <td class="users-status"> <label class="users-status-active">Active</label> </td>
                                    <td> <%= user.getUserRole() %> </td>
                                    <td class="users-account"> <i class="ti-unlock text-success"></i> </td>
                                    <td class="users-action">
                                        <a href="UserDetails?userId=<%=user.getUserId()%>" class="user_list_update">
                                            <i class="ti-pencil-alt"></i>
                                        </a>
                                        <a  href="#" class="user_list_delete" data-toggle="modal" data-target=<%=targetDataId%>>
                                            <i class="fa fa-trash text-danger"></i>
                                        </a>
                                        <div class="modal fade" id=<%=dataId%>>
                                            <div class="modal-dialog modal-sm">
                                                <div class="modal-content">
                                                    <div class="modal-body">
                                                        <h4>Are you sure?</h4>
                                                        <a class="btn btn-success" href="Delete?userId=<%=user.getUserId()%>">Yes</a>
                                                        <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <%  index++; } %>
                                <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%
                        Long numberOfUsers = (Long) request.getAttribute("numberOfUsers");
                        int numberOfUsersToDisplay = (int) request.getAttribute("numberOfUsersToDisplay");
                        if(usersDetails.size()>0) {
                    %>
                    <div>Records ${startNumber} - ${endNumber} of ${numberOfUsers}</div>
                    <% }
                        for(int i=0; i<=numberOfUsers/numberOfUsersToDisplay; i++) {
                    %>
                    <button type="submit" class="users-main-page-number" name="active-page-id" value="<%=i+1 %>">
                        <%=i+1%>
                    </button>
                    <%}%>
                </form>
            </div>
        </main>
    </div>
</div>
<%
    session.setAttribute("message",null);
%>
<script>
    document.getElementById("users-main-select-maximum-number-to-diplay").value = "${numberOfUsersToDisplay}";
    let pages = document.getElementsByClassName("users-main-page-number");
    for(let page of pages) {
        if(page.value === "${active_page}") {
            page.className = "users-main-active-page-number";
        }
    }
    if(!dashboardPermissions[0])
        document.getElementById("user_list_new_user").style.display = "none";
    if(!dashboardPermissions[1]) {
        const deletePermissions = document.getElementsByClassName("user_list_delete");
        for (const deleteContainer of deletePermissions )
            deleteContainer.style.display = "none";
    }
    if(!dashboardPermissions[2]) {
        const updatePermissions = document.getElementsByClassName("user_list_update");
        for (const update of updatePermissions )
            update.style.display = "none";
    }
    if( !dashboardPermissions[1] && !dashboardPermissions[2]){
        const userActions = document.getElementsByClassName("users-action");
        for (const action of userActions )
            action.style.display = "none";
    }
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="script/dashboard.js"></script>
</body>
</html>