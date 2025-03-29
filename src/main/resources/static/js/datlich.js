document.addEventListener("DOMContentLoaded", function () {
    const spaSelect = document.getElementById("spa");
    const serviceSelect = document.getElementById("service");
    const specialistSelect = document.getElementById("specialist");
    const bookingForm = document.getElementById("bookingForm");

    // Danh sách dịch vụ của từng Spa
    const services = {
        spa1: ["Tắm trắng", "Trị mụn", "Mát xa thư giãn", "Tiêm Meso", "Phun xăm"],
        spa2: ["Điều trị da", "Giảm béo", "Gội đầu dưỡng sinh", "Massage toàn thân"],
        spa3: ["Nâng cơ trẻ hóa", "Cấy collagen", "Trị nám tàn nhang", "Xóa xăm"],
    };

    // Danh sách chuyên viên của từng Spa
    const specialists = {
        spa1: ["Nguyễn Thị Mai", "Trần Văn Phúc", "Hoàng Lan Anh"],
        spa2: ["Lê Bảo Ngọc", "Phạm Hồng Sơn", "Vũ Minh Trang"],
        spa3: ["Đặng Thùy Dung", "Ngô Hoàng Nam", "Trịnh Kim Phương"],
    };

    // Khi chọn Spa, cập nhật danh sách dịch vụ và chuyên viên
    spaSelect.addEventListener("change", function () {
        const selectedSpa = spaSelect.value;

        // Xóa danh sách cũ
        serviceSelect.innerHTML = '';
        specialistSelect.innerHTML = '';

        // Thêm lại option mặc định
        const defaultServiceOption = document.createElement("option");
        defaultServiceOption.value = "";
        defaultServiceOption.textContent = "-- Chọn dịch vụ --";
        serviceSelect.appendChild(defaultServiceOption);

        const defaultSpecialistOption = document.createElement("option");
        defaultSpecialistOption.value = "";
        defaultSpecialistOption.textContent = "-- Chọn chuyên viên --";
        specialistSelect.appendChild(defaultSpecialistOption);

        if (selectedSpa && services[selectedSpa]) {
            services[selectedSpa].forEach(service => {
                let option = document.createElement("option");
                option.value = service;
                option.textContent = service;
                serviceSelect.appendChild(option);
            });

            specialists[selectedSpa].forEach(specialist => {
                let option = document.createElement("option");
                option.value = specialist;
                option.textContent = specialist;
                specialistSelect.appendChild(option);
            });
        }
    });

    // Xử lý đặt lịch
    bookingForm.addEventListener("submit", function (event) {
        event.preventDefault();

        if (!spaSelect.value) {
            alert("Vui lòng chọn Spa trước khi đặt lịch!");
            return;
        }

        // Hiển thị modal Bootstrap
        const successModal = new bootstrap.Modal(document.getElementById("successModal"));
        successModal.show();

        // Xóa nội dung form
        bookingForm.reset();
    });

    console.log("✅ File datlich.js đã được tải thành công!");
});
