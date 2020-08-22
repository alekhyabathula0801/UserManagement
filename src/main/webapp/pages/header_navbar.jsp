<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="dashboard-nav">
    <div class="dashboard-nav-left">
        <div class="dashboard-brand">User Management</div>
        <button type="button" onclick="toggleSideToolBar()" class="btn-toggle-fullwidth navbar-btn">
            <i class="ti-arrow-circle-right" id="header-button-icon"></i>
        </button>
    </div>
    <div class="dashboard-nav-right dropdown">
        <a class="dashboard-user-name dropdown-toggle" data-toggle="dropdown">${userName}</a>
        <div class="dashboard-nav-right-dropdown-menu dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="#"><i class="ti-user"></i>Profile</a>
            <a class="dropdown-item"href="#"><i class="ti-power-off"></i>Logout</a>
        </div>
    </div>
</nav>
