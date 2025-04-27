package ut.edu.project_skincarebooking.controller_for_web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.models.*;
import ut.edu.project_skincarebooking.services.interF.*;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/datlich")
public class AppointmentWebController {

    private final AppointmentService appointmentService;
    private final SkinTherapistService therapistService;
    private final ServiceService serviceService;
    private final CustomerService customerService;

    // Hiển thị form đặt lịch
    @GetMapping
    public String showBookingForm() {
        return "datlich";
    }

    // Xử lý đặt lịch
    @PostMapping("/create")
    public String createAppointment(@RequestParam("name") String name,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("address") String address,
                                    @RequestParam("services") List<Long> serviceIds,
                                    @RequestParam("specialist") String specialistCode,
                                    @RequestParam("date") String dateStr,
                                    @RequestParam("time") String timeStr,
                                    HttpSession session) {

        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            System.out.println("🚫 Không có customerId trong session → redirect login");
            return "redirect:/login";
        }

        Customer customer;
        try {
            customer = customerService.getCustomerById(customerId);
        } catch (Exception e) {
            System.out.println("🚫 Không tìm thấy customer từ ID → redirect login");
            return "redirect:/login";
        }

        if (customer.getName() == null || customer.getName().isEmpty()) {
            customer.setName(name);
            customerService.saveCustomer(customer);
        }

        // Lấy danh sách dịch vụ
        List<ServiceEntity> selectedServices = new ArrayList<>();
        for (Long serviceId : serviceIds) {
            ServiceEntity service = serviceService.getServiceById(serviceId);
            if (service != null) {
                selectedServices.add(service);
            }
        }

        if (selectedServices.isEmpty()) {
            return "redirect:/datlich"; // Không có dịch vụ nào hợp lệ
        }

        // Lấy chuyên viên
        SkinTherapist therapist;
        if ("khong_chon_chuyen_vien".equals(specialistCode) || specialistCode.isEmpty()) {
            List<SkinTherapist> allTherapists = therapistService.getAllTherapists();
            therapist = allTherapists.get(new Random().nextInt(allTherapists.size()));
        } else {
            Long therapistId = Long.parseLong(specialistCode);
            therapist = therapistService.getTherapistById(therapistId);
        }

        // Chuẩn hóa thời gian
        if (timeStr.length() == 5) {
            timeStr += ":00";
        }

        // Tạo lịch hẹn
        Appointment appointment = new Appointment();
        appointment.setCustomerName(name);
        appointment.setCustomer(customer);
        appointment.setTherapist(therapist);
        appointment.setDate(LocalDate.parse(dateStr));
        appointment.setTime(LocalTime.parse(timeStr));
        appointment.setStatus("Unpaid");
        appointment.setServices(selectedServices); // 🔥 lưu danh sách dịch vụ

        System.out.println("🚀 Gọi addAppointment()");
        appointmentService.addAppointment(appointment);
        System.out.println("✅ Gọi xong addAppointment()");
        System.out.println("🟢 [DEBUG] Bắt đầu xử lý tạo appointment");
        System.out.println("Tên: " + name);
        System.out.println("SĐT: " + phone);
        System.out.println("Địa chỉ: " + address);
        System.out.println("Chuyên viên: " + specialistCode);
        System.out.println("Ngày: " + dateStr);
        System.out.println("Giờ: " + timeStr);
        System.out.println("Dịch vụ (IDs): " + serviceIds);

        Object customerIdObj = session.getAttribute("customerId");
        if (customerIdObj == null) {
            System.out.println("❌ [LỖI] Chưa đăng nhập (customerId null)");
            return "redirect:/login";
        }



        return "redirect:/history";
    }

    // Hiển thị lịch sử đặt lịch


    // Đánh dấu đã thanh toán
    @GetMapping("/pay/{id}")
    public String markAsPaid(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        appointment.setStatus("Paid");
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/history";
    }
}
