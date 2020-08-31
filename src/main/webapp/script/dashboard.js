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
    setCheckBoxes(element.className,element.checked);
}

function setCheckBoxes(className,booleanResult) {
    let checkBoxes = document.getElementsByClassName(className);
    for(i=0;i<checkBoxes.length;i++){
        checkBoxes[i].checked = booleanResult;
    }
}

function setPermissions() {
    let userRole = document.getElementById("new-user-role");
    if(userRole.value === "User") {
        setCheckBoxes("permission-add",false);
        setCheckBoxes("permission-delete",false);
        setCheckBoxes("permission-modify",false);
        setCheckBoxes("permission-read",false);
        setCheckBoxes("permission-user",true);
    } else {
        setCheckBoxes("permission-add",true);
        setCheckBoxes("permission-delete",true);
        setCheckBoxes("permission-modify",true);
        setCheckBoxes("permission-read",true);
    }
}
