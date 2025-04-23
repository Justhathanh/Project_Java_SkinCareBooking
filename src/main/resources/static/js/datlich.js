document.addEventListener("DOMContentLoaded", function () {
    const bookingForm = document.getElementById("bookingForm");

    bookingForm.addEventListener("submit", function (event) {
        event.preventDefault(); // vẫn giữ để kiểm tra dữ liệu

        const name = document.getElementById("name").value.trim();
        const phone = document.getElementById("phone").value.trim();
        const address = document.getElementById("address").value.trim();
        const service = document.getElementById("service").value;
        const specialist = document.getElementById("specialist").value;
        const date = document.getElementById("date").value;
        const time = document.getElementById("time").value;

        if (!name || !phone || !address || !service || !specialist || !date || !time) {
            alert("Vui lòng nhập đầy đủ thông tin trước khi đặt lịch!");
            return;
        }

        // Hiển thị modal thành công
        const successModal = new bootstrap.Modal(document.getElementById("successModal"));
        successModal.show();

        // Gửi form về controller sau khi hiện modal 2.5 giây
        setTimeout(() => {
            bookingForm.submit();
        }, 2500);
    });

    console.log("✅ File datlich.js đã được tải thành công!");
});
