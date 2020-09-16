$(document).ready(function(){
    $(".dropify").dropify();
});

document.getElementById("dateId").max = new Date().toISOString().split("T")[0];

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

function showDropify() {
    document.getElementById("update-user-image").style.display = "none";
    document.getElementById("update-user-image-input-container").style.display = "flex";
}

function toggleActiveInactive(element) {
    if(element.checked === true) {
        document.getElementById("user-details-status-input").checked = true;
        document.getElementById("user-details-status").innerText = "Active";
    } else {
        document.getElementById("user-details-status-input").checked = false;
        document.getElementById("user-details-status").innerText = "Inactive";
    }
}
