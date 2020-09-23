<div class="webpage">
    <%@include file = "header_navbar.jsp" %>
    <div class="webpage-body">
        <%@include file = "side_tool_bar.jsp" %>
        <main class="webpage-main" id="main">
            <header class="webpage-header">
                <div class="webpage-header-left">
                    <span class="webpage-header-left-welcome" id="webpage-header-left-welcome-tittle"></span>
                    <span id="webpage-header-left-welcome-message"></span>
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
                            <input id="webpage-add" type="checkbox" class="switch-input" disabled>
                            <span class="switch-label"></span>
                            <span class="switch-handle"></span>
                        </label>
                    </div>
                    <div class="webpage-main-content-permission">
                        <label>Delete Permission</label>
                        <label class="switch switch-3d switch-primary">
                            <input id="webpage-delete" type="checkbox" class="switch-input" disabled>
                            <span class="switch-label"></span>
                            <span class="switch-handle"></span>
                        </label>
                    </div>
                    <div class="webpage-main-content-permission">
                        <label>Modify Permission</label>
                        <label class="switch switch-3d switch-primary">
                            <input id="webpage-modify" type="checkbox" class="switch-input" disabled>
                            <span class="switch-label"></span>
                            <span class="switch-handle"></span>
                        </label>
                    </div>
                    <div class="webpage-main-content-permission">
                        <label>Read Permission</label><label class="switch switch-3d switch-primary">
                        <input id="webpage-read" type="checkbox" class="switch-input" disabled>
                        <span class="switch-label"></span>
                        <span class="switch-handle"></span>
                    </label>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<script src="script/webpage.js"></script>
