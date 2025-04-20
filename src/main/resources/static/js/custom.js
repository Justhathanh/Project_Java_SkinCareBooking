function togglePassword() {
    const passField = document.getElementById("password");
    if (passField.type === "password") {
        passField.type = "text";
    } else {
        passField.type = "password";
    }
}
