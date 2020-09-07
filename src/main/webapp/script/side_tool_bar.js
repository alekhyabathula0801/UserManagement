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
