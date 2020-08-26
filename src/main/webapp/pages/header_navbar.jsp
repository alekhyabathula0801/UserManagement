<nav class="header-nav">
    <div class="header-nav-left">
        <div class="header-brand">User Management</div>
        <button type="button" onclick="toggleSideToolBar()" class="btn-toggle-fullwidth navbar-btn">
            <i class="ti-arrow-circle-right" id="header-button-icon"></i>
        </button>
    </div>
    <div class="header-nav-right dropdown">
        <a class="header-user-name dropdown-toggle" data-toggle="dropdown">${userName}</a>
        <div class="header-nav-right-dropdown-menu dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="#"><i class="ti-user"></i>Profile</a>
            <a class="dropdown-item"href="Logout"><i class="ti-power-off"></i>Logout</a>
        </div>
    </div>
</nav>