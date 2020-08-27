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

function checkPassword() {
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;
    console.log(password.toString() + " " + confirmPassword);
    if(password !== confirmPassword) {
        document.getElementById("password-message").innerHTML = "Password doesn't match with confirm password";
        return false;
    } else {
        document.getElementById("password-message").innerHTML = "";
        return true;
    }
}

function toggleCheckBoxes(element) {
    console.log("element class name " + element.className)
    let addCheckBoxes = document.getElementsByClassName(element.className);
    if(element.checked) {
        for(i=0;i<addCheckBoxes.length;i++){
            addCheckBoxes[i].checked = true;
        }
    } else {
        for(i=0;i<addCheckBoxes.length;i++){
            addCheckBoxes[i].checked = false;
        }
    }
}