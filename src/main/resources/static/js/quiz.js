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

    const yesButton = document.getElementById("yes-btn");
    const noButton = document.getElementById("no-btn");
    const backButton = document.getElementById("back-btn");
    const services = document.getElementById("services");
    const noServiceBtn = document.getElementById("no-service-btn");
    const confirmBtn = document.getElementById("service-confirm");

    yesButton.style.display = "none";
    noButton.style.display = "none";
    backButton.style.display = "block";

    if (want) {
        services.style.display = "flex";
        noServiceBtn.style.display = "none";
        confirmBtn.style.display = "block"; // Hiện nút xác nhận khi có dịch vụ
    } else {
        services.style.display = "none";
        noServiceBtn.style.display = "block";
        confirmBtn.style.display = "none"; // Không hiện nút xác nhận khi không chọn
    }
}

function goBack() {
    document.getElementById("yes-btn").style.display = "inline-block";
    document.getElementById("no-btn").style.display = "inline-block";
    document.getElementById("back-btn").style.display = "none";
    document.getElementById("services").style.display = "none";
    document.getElementById("no-service-btn").style.display = "none";
    document.getElementById("service-confirm").style.display = "none";
}





function showResult() {
    const services = Array.from(document.querySelectorAll('#services input:checked')).map(cb => cb.value);
    document.getElementById("quiz").style.display = "none";
    document.getElementById("result").style.display = "block";

    let text = `<strong>Da bạn là loại:</strong> ${skinTypes.join(', ')}<br><strong>Nhu cầu bạn đã chọn:</strong> ${needs.join(', ')}<br>`;
    if (services.length > 0) {
        text += `<strong>Dịch vụ bạn đã chọn:</strong> ${services.join(', ')}`;
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
        text += `<strong>KAMOI gợi ý cho bạn dịch vụ:</strong> ${suggest.join(', ')}`;
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

