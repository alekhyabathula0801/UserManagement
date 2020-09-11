function toggleSideToolBar() {
    let sideMenu = document.getElementById("side-menu");
    let buttonIcon = document.getElementById("header-button-icon");
    if(sideMenu.style.display === "block") {
        sideMenu.style.display = "none";
        buttonIcon.className = "ti-arrow-circle-left";
    } else {
        sideMenu.style.display = "block";
        buttonIcon.className = "ti-arrow-circle-right";
    }
}

function toggleMinifySideToolBar() {
    let minifiedSideToolBar = document.getElementById("side-menu-minified");
    let sideToolBar = document.getElementById("side-menu-bar");
    if(minifiedSideToolBar.style.display === "block") {
        minifiedSideToolBar.style.display = "none";
        sideToolBar.style.display = "block";
    } else {
        minifiedSideToolBar.style.display = "block";
        sideToolBar.style.display = "none";
    }
}

