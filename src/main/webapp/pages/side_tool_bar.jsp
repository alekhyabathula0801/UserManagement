<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside class="dashboard-side-menu">

    <div class="dashboard-side-menu-content">
        <div>
            <a href="dashboard.jsp" class="side-menu-dashboard">
                <i class="ti-dashboard menu-left-icon"></i>
                <span class="title">Dashboard</span>
                <span class="label label-success">UPDATED</span>
            </a>
        </div>
        <div>
            <a href="#" class="side-menu-web-pages collapsed" data-toggle="collapse">
                <i class="ti-files menu-left-icon"></i>
                <span class="title">Webpages</span>
                <i class="icon-submenu ti-angle-left menu-right-icon"></i>
            </a>
        </div>
        <div>
            <a href="#" data-toggle="collapse" class="side-menu-users collapsed" >
                <i class="ti-user menu-left-icon"></i>
                <span class="title">Users</span>
                <i class="icon-submenu ti-angle-left menu-right-icon"></i></a>
        </div>
        <div>
            <a href="#" class="side-menu-profile">
                <i class="ti-id-badge menu-left-icon"></i>
                <span class="title">Profile</span>
            </a>
        </div>
        <div>
            <a href="#" data-toggle="collapse" class="side-menu-settings collapsed" >
                <i class="ti-settings menu-left-icon"></i>
                <span class="title">Settings</span>
                <i class="icon-submenu ti-angle-left menu-right-icon"></i></a>
        </div>
        <div>
            <a href="#" class="side-menu-logout">
                <i class="ti-power-off menu-left-icon"></i>
                <span class="title">Logout</span>
            </a>
        </div>
        <button type="button" class="button-toggle-minified">
            <i class="ti-arrows-horizontal"></i>
        </button>
    </div>
</aside>