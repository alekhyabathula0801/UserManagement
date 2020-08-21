<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="dashboard-nav">
    <div class="dashboard-nav-left">
        <div class="dashboard-brand">User Management</div>
        <i class="ti-arrow-circle-right"></i>
    </div>
    <div class="dashboard-nav-right dropdown">
        <a class="dashboard-user-name dropdown-toggle" data-toggle="dropdown">${userName}</a>
        <div class="dashboard-nav-right-dropdown-menu dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="#"><i class="ti-user"></i>Profile</a>
            <a class="dropdown-item"href="#"><i class="ti-power-off"></i>Logout</a>
        </div>
    </div>
</nav>
