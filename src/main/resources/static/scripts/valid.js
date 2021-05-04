
let log_password = document.getElementById("password")
    , passwordConfirm = document.getElementById("passwordConfirm");

function validatePassword() {
    if (log_password.value !== passwordConfirm.value) {
        passwordConfirm.setCustomValidity("Passwords Don't Match");
    } else {
        passwordConfirm.setCustomValidity('');
    }
}

log_password.onchange = validatePassword;
passwordConfirm.onkeyup = validatePassword;