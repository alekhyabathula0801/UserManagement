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
<%@include file = "webpage.jsp" %>
<script>
    setWebpageWelcomeName(1);
    function setWebpageWelcomeName(pageNumber) {
        document.getElementById("webpage-header-left-welcome-tittle").innerText = "Webpage "+pageNumber;
        document.getElementById("webpage-header-left-welcome-message").innerText = "Permissions granted for webpage "+pageNumber;
    }
    if(webpage1Permissions[0])
        document.getElementById("webpage-add").checked = true;
    if(webpage1Permissions[1])
        document.getElementById("webpage-delete").checked = true;
    if(webpage1Permissions[2])
        document.getElementById("webpage-modify").checked = true;
    if(webpage1Permissions[3])
        document.getElementById("webpage-read").checked = true;
</script>

<script src="https://coreui.io/docs-assets/js/vendor/popper.min.js"></script>
<script src="https://coreui.io/dist/js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="script/side_tool_bar.js"></script>
<script src="script/webpage.js"></script>
</body>
</html>