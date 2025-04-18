document.addEventListener("DOMContentLoaded", function () {
    // Smooth scroll for buttons
    document.querySelector(".btn-light").addEventListener("click", function (event) {
        event.preventDefault();
        document.querySelector("#services").scrollIntoView({ behavior: "smooth" });
    });

    // Navbar shrink effect on scroll
    window.addEventListener("scroll", function () {
        let navbar = document.querySelector(".navbar");
        if (window.scrollY > 50) {
            navbar.classList.add("shadow-sm", "bg-white");
        } else {
            navbar.classList.remove("shadow-sm", "bg-white");
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const skinOptions = document.querySelectorAll(".option");
    const needOptions = document.querySelectorAll(".question .option");
    const serviceCheckboxes = document.querySelectorAll(".service-list input[type='checkbox']");

    skinOptions.forEach(option => {
        option.addEventListener('click', function () {
            toggleSelect(this, 'skin');
        });
    });

    needOptions.forEach(option => {
        option.addEventListener('click', function () {
            toggleSelect(this, 'need');
        });
    });

    serviceCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            updateServiceSelection();
        });
    });
});

let skinTypes = [], needs = [];

function toggleSelect(el, group) {
    el.classList.toggle("selected");
    const val = el.innerText;
    if (group === 'skin') {
        if (skinTypes.includes(val)) {
            skinTypes.splice(skinTypes.indexOf(val), 1);
        } else {
            skinTypes.push(val);
        }
    } else if (group === 'need') {
        if (needs.includes(val)) {
            needs.splice(needs.indexOf(val), 1);
        } else {
            needs.push(val);
        }
    }
}

function chooseService(want) {
    if (skinTypes.length === 0 || needs.length === 0) {
        showModal("Bạn vui lòng chọn loại da và nhu cầu trước nha!");
        return;
    }
    document.getElementById("services").style.display = want ? "flex" : "none";
    document.getElementById("no-service-btn").style.display = want ? "none" : "block";
}

function showResult() {
    const services = Array.from(document.querySelectorAll('#services input:checked')).map(cb => cb.value);
    document.getElementById("quiz").style.display = "none";
    document.getElementById("result").style.display = "block";

    let text = `<strong>Loại da:</strong> ${skinTypes.join(', ')}<br><strong>Nhu cầu:</strong> ${needs.join(', ')}<br>`;
    if (services.length > 0) {
        text += `<strong>Dịch vụ bạn chọn:</strong> ${services.join(', ')}`;
    } else {
        let suggest = [];
        if (needs.includes("Mụn")) suggest.push("Trị Mụn Chuyên Sâu");
        if (needs.includes("Nám, tàn nhang")) suggest.push("Điều trị nám, tàn nhang");
        if (needs.includes("Dưỡng trắng")) suggest.push("Dưỡng trắng da");
        if (needs.includes("Cấp ẩm")) suggest.push("Cấy tinh chất cấp ẩm");
        if (needs.includes("Sẹo rỗ, thâm")) suggest.push("Điều trị sẹo rỗ, thâm");
        if (needs.includes("Thư giãn")) suggest.push("Massage thư giãn & detox");
        if (needs.includes("Chống lão hóa")) suggest.push("Nâng cơ bằng RF");
        if (suggest.length === 0) suggest.push("Chăm Sóc Da Mặt Cơ Bản");
        text += `<strong>Hệ thống gợi ý:</strong> ${suggest.join(', ')}`;
    }

    document.getElementById("summary").innerHTML = text;
}

function showModal(msg) {
    document.getElementById("modal-message").innerText = msg;
    document.getElementById("custom-modal").style.display = "block";
}

function closeModal() {
    document.getElementById("custom-modal").style.display = "none";
}

function updateServiceSelection() {}
// Hàm này có thể được sử dụng để xử lý bất kỳ bản cập nhật động nào dựa trên hộp kiểm dịch vụ}

