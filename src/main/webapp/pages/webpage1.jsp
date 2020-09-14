<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://coreui.io/docs-assets/css/style.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/webpage.css">
    <link rel="stylesheet" href="css/header_navbar.css">
    <link rel="stylesheet" href="css/side_tool_bar.css">
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if (session.getAttribute("user")==null & session.getAttribute("webpage1Permissions")==null) {
        session.setAttribute("message","Please Login");
        response.sendRedirect("login");
    } else if (session.getAttribute("webpage1Permissions")==null) {
        response.sendRedirect("page_not_found");
    }
%>
<div class="webpage">
    <%@include file = "header_navbar.jsp" %>
    <div class="webpage-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="webpage-main" id="main">
            <header class="webpage-header">
                <div class="webpage-header-left">
                    <span class="webpage-header-left-welcome">Webpage 1</span>
                    <span>Permissions granted for webpage 1</span>
                </div>
                <div class="webpage-header-right">
                    <a href="dashboard"><i class="fa fa-home"></i>Home</a>
                    <span> / Dashboard </span>
                </div>
            </header>
            <div class="webpage-main-content">
                <div class="webpage-main-content-header">
                    Webpage Permissions
                </div>
                <div class="webpage-main-content-details">
                    <div class="webpage-main-content-permission">
                        <label>Add Permission</label>
                        <label class="switch switch-3d switch-primary">
                        <input id="webpage1-add" type="checkbox" class="switch-input" disabled>
                        <span class="switch-label"></span>
                        <span class="switch-handle"></span>
                    </label>
                    </div>
                    <div class="webpage-main-content-permission">
                        <label>Delete Permission</label>
                        <label class="switch switch-3d switch-primary">
                            <input id="webpage1-delete" type="checkbox" class="switch-input" disabled>
                            <span class="switch-label"></span>
                            <span class="switch-handle"></span>
                        </label>
                    </div>
                    <div class="webpage-main-content-permission">
                        <label>Modify Permission</label>
                        <label class="switch switch-3d switch-primary">
                            <input id="webpage1-modify" type="checkbox" class="switch-input" disabled>
                            <span class="switch-label"></span>
                            <span class="switch-handle"></span>
                        </label>
                    </div>
                    <div class="webpage-main-content-permission">
                        <label>Read Permission</label><label class="switch switch-3d switch-primary">
                        <input id="webpage1-read" type="checkbox" class="switch-input" disabled>
                        <span class="switch-label"></span>
                        <span class="switch-handle"></span>
                    </label>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<script>
    if(webpage1Permissions[0])
        document.getElementById("webpage1-add").checked = true;
    if(webpage1Permissions[1])
        document.getElementById("webpage1-delete").checked = true;
    if(webpage1Permissions[2])
        document.getElementById("webpage1-modify").checked = true;
    if(webpage1Permissions[3])
        document.getElementById("webpage1-read").checked = true;
</script>

<script src="https://coreui.io/docs-assets/js/vendor/popper.min.js"></script>
<script src="https://coreui.io/dist/js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="script/side_tool_bar.js"></script>
</body>
</html>