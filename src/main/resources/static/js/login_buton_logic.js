const loginBtn = document.getElementById("loginBtn");
const username = document.getElementById("username");
const password = document.getElementById("password");
let form = document.getElementById("loginForm");

function isFormValid() {
    return username.value.trim() !== "" && password.value.trim() !== "";
}

loginBtn.addEventListener("mouseenter", function () {
    if (!isFormValid()) {
        const offsetX = Math.floor(Math.random() * 300) - 150;
        const offsetY = Math.floor(Math.random() * 100) - 50;
        loginBtn.style.position = "relative";
        loginBtn.style.left = `${offsetX}px`;
        loginBtn.style.top = `${offsetY}px`;
    } else {
        loginBtn.style.position = "static";
    }
});

username.addEventListener("input", resetBtnPosition);
password.addEventListener("input", resetBtnPosition);

function resetBtnPosition() {
    if (isFormValid()) {
        loginBtn.style.position = "static";
    }
}
