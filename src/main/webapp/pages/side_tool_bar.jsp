<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside class="side-menu" id="side-menu" style="display: block">
    <div class="side-menu-minified" id="side-menu-minified" style="display: none">
        <div class="dropright">
            <button class="side-minified-data" data-toggle="dropdown"><i class="ti-dashboard"></i></button>
            <div class="dropdown-menu side-minified-menu">
                <a href="Dashboard" class="dropdown-item side-minified-menu-tittle">Dashboard</a>
            </div>
        </div>
        <div class="dropright">
            <button class="side-minified-data" data-toggle="dropdown"><i class="ti-files"></i></button>
            <div class="dropdown-menu side-minified-menu">
                <a href="#" class="dropdown-item side-minified-menu-data side-minified-menu-tittle">Webpages</a>
                <a href="webpage1" class="dropdown-item side-minified-menu-data">Webpage 1</a>
                <a href="#" class="dropdown-item side-minified-menu-data">Webpage 2</a>
                <a href="#" class="dropdown-item side-minified-menu-data">Webpage 3</a>
                <a href="#" class="dropdown-item side-minified-menu-data">Blank Page</a>
            </div>
        </div>
        <div class="dropright">
            <button class="side-minified-data" data-toggle="dropdown"><i class="ti-user"></i></button>
            <div class="dropdown-menu side-minified-menu">
                <a href="#" class="dropdown-item side-minified-menu-data side-minified-menu-tittle">Users</a>
                <a href="new_user" class="dropdown-item side-minified-menu-data">New User</a>
                <a href="user_list" class="dropdown-item side-minified-menu-data">User List</a>
            </div>
        </div>
        <div class="dropright">
            <button class="side-minified-data" data-toggle="dropdown"><i class="ti-id-badge"></i></button>
            <div class="dropdown-menu side-minified-menu">
                <a href="profile" class="dropdown-item side-minified-menu-tittle">Profile</a>
            </div>
        </div>
        <div class="dropright">
            <button class="side-minified-data" data-toggle="dropdown"><i class="ti-files"></i></button>
            <div class="dropdown-menu side-minified-menu">
                <a href="#" class="dropdown-item side-minified-menu-data side-minified-menu-tittle">Settings</a>
                <a href="#" class="dropdown-item side-minified-menu-data">Authentication</a>
                <a href="#" class="dropdown-item side-minified-menu-data">Email SMTP</a>
                <a href="#" class="dropdown-item side-minified-menu-data">Database Connection</a>
            </div>
        </div>
        <a href="Logout" class="side-minified-data"><i class="ti-power-off"></i></a>
        <button type="button" class="button-toggle-minified" onclick="toggleMinifySideToolBar()">
            <i class="ti-arrows-horizontal"></i>
        </button>
    </div>
    <div class="side-menu-content" id="side-menu-bar" >
        <div id="side_bar_dashboard">
            <a href="Dashboard" class="side-menu-sub-folder side-menu-details">
                <div class="side-menu-title-and-icon">
                    <i class="ti-dashboard menu-left-icon"></i>
                    <span class="title">Dashboard</span>
                </div>
                <span class="label label-success">UPDATED</span>
            </a>
        </div>
        <div>
            <a class="side-menu-web-pages side-menu-sub-folder side-menu-details" data-toggle="collapse"
               href="#side-menu-webpages">
                <div class="side-menu-title-and-icon">
                    <i class="ti-files menu-left-icon"></i>
                    <span class="title">Webpages</span>
                </div>
                <i class="icon-submenu ti-angle-left menu-right-icon"></i>
            </a>
            <div id="side-menu-webpages" class="tool-bar-submenu collapse" data-parent="#side-menu-bar" >
                <a class="webpage" id="side_bar_webpage1" href="webpage1">Web Page 1</a>
                <a class="webpage" id="side_bar_webpage2" href="#">Web Page 2</a>
                <a class="webpage" id="side_bar_webpage3" href="#">Web Page 3</a>
                <a class="webpage"href="#">Blank Page</a>
            </div>
        </div>
        <div id="side_bar_users">
            <a class="side-menu-users side-menu-sub-folder side-menu-details" data-toggle="collapse"
               href="#side-menu-user-options">
                <div class="side-menu-title-and-icon">
                    <i class="ti-user menu-left-icon"></i>
                    <span class="title">Users</span>
                </div>
                <i class="icon-submenu ti-angle-left menu-right-icon"></i>
            </a>
            <div id="side-menu-user-options" class="tool-bar-submenu collapse" data-parent="#side-menu-bar" >
                <a class="user-options" id="side_bar_new_user" href="new_user">New User</a>
                <a class="user-list"href="user_list">
                    <span class="title">User List</span>
                    <span class="label label-success">UPDATED</span>
                </a>
            </div>
        </div>
        <div class="side-menu-content-field">
            <a href="profile" class="side-menu-profile side-menu-details">
                <i class="ti-id-badge menu-left-icon"></i>
                <span class="title">Profile</span>
            </a>
        </div>
        <div id="side_bar_settings">
            <a class="side-menu-settings side-menu-sub-folder side-menu-details" data-toggle="collapse"
               href="#side-menu-settings">
                <div class="side-menu-title-and-icon">
                    <i class="ti-settings menu-left-icon"></i>
                    <span class="title">Settings</span>
                </div>
                <i class="icon-submenu ti-angle-left menu-right-icon"></i>
            </a>
            <div id="side-menu-settings" class="tool-bar-submenu collapse" data-parent="#side-menu-bar" >
                <a class="menu-settings" href="#">Authentication</a>
                <a class="menu-settings"href="#">Email SMTP</a>
                <a class="menu-settings" href="#">Database Connection</a>
            </div>
        </div>
        <div class="side-menu-content-field">
            <a href="Logout" class="side-menu-logout side-menu-details">
                <i class="ti-power-off menu-left-icon"></i>
                <span class="title">Logout</span>
            </a>
        </div>
        <button type="button" class="button-toggle-minified" onclick="toggleMinifySideToolBar()">
            <i class="ti-arrows-horizontal"></i>
        </button>
    </div>
</aside>
<script>
    const dashboardPermissions = <%=session.getAttribute("dashboardPermissions")%>;
    const settingsPermissions= <%=session.getAttribute("settingsPermissions")%>;
    const webpage1Permissions= <%=session.getAttribute("webpage1Permissions")%>;
    const webpage2Permissions= <%=session.getAttribute("webpage2Permissions")%>;
    const webpage3Permissions= <%=session.getAttribute("webpage3Permissions")%>;
    if(dashboardPermissions === null) {
        document.getElementById("side_bar_dashboard").style.display = "none";
        document.getElementById("side_bar_users").style.display = "none";
    }
    if(!dashboardPermissions[0])
        document.getElementById("side_bar_new_user").style.display = "none";
    if(settingsPermissions === null)
        document.getElementById("side_bar_settings").style.display = "none";
    if(webpage1Permissions === null)
        document.getElementById("side_bar_webpage1").style.display = "none";
    if(webpage2Permissions === null)
        document.getElementById("side_bar_webpage2").style.display = "none";
    if(webpage3Permissions === null)
        document.getElementById("side_bar_webpage3").style.display = "none";
</script>