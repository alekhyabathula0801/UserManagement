function toggleSideToolBar() {
    let sideMenu = document.getElementById("side-menu");
    let buttonIcon = document.getElementById("header-button-icon");
    let main = document.getElementById("main");
    if(sideMenu.style.display === "block") {
        sideMenu.style.display = "none";
        buttonIcon.className = "ti-arrow-circle-left";
        main.style.width = "100%";
    } else {
        sideMenu.style.display = "block";
        buttonIcon.className = "ti-arrow-circle-right";
        main.style.width = "73%";
    }
}