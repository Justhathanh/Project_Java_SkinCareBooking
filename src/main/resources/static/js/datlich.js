document.addEventListener("DOMContentLoaded", function () {
    $('#services').select2({
        placeholder: "-- Chọn dịch vụ --",
        allowClear: true,
        width: '100%'
    });

    const form = document.getElementById("bookingForm");
    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const services = $('#services').val(); // ❗ Lấy đúng cách
        const name = document.getElementById("name").value.trim();
        const phone = document.getElementById("phone").value.trim();
        const address = document.getElementById("address").value.trim();
        const specialist = document.getElementById("specialist").value;
        const date = document.getElementById("date").value;
        const time = document.getElementById("time").value;

        if (!name || !phone || !address || !services || services.length === 0 || !specialist || !date || !time) {
            alert("Vui lòng nhập đầy đủ thông tin trước khi đặt lịch!");
            return;
        }

        const modal = new bootstrap.Modal(document.getElementById("successModal"));
        modal.show();

        setTimeout(() => {
            form.submit();
        }, 2500);
    });
});
