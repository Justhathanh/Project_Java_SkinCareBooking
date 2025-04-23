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
                                    @RequestParam("service") Long serviceId,
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

// ✅ Cập nhật tên nếu thiếu
        if (customer.getName() == null || customer.getName().isEmpty()) {
            customer.setName(name);
            customerService.saveCustomer(customer);
        }

        // Lấy dịch vụ
        ServiceEntity selectedService = serviceService.getServiceById(serviceId);
        if (selectedService == null) return "redirect:/datlich";

        // Lấy chuyên viên
        SkinTherapist therapist;
        if ("khong_chon_chuyen_vien".equals(specialistCode) || specialistCode.isEmpty()) {
            List<SkinTherapist> allTherapists = therapistService.getAllTherapists();
            therapist = allTherapists.get(new Random().nextInt(allTherapists.size()));
        } else {
            Long therapistId = Long.parseLong(specialistCode);
            therapist = therapistService.getTherapistById(therapistId);
        }

        // Chuẩn hóa thời gian: hh:mm -> hh:mm:00
        if (timeStr.length() == 5) {
            timeStr += ":00";
        }

        // Tạo lịch hẹn
        Appointment appointment = new Appointment();
        appointment.setCustomerName(name); // hiển thị riêng nếu cần
        appointment.setCustomer(customer);
        appointment.setService(selectedService);
        appointment.setTherapist(therapist);
        appointment.setDate(LocalDate.parse(dateStr));
        appointment.setTime(LocalTime.parse(timeStr));
        appointment.setStatus("Unpaid");

        System.out.println("🚀 Gọi addAppointment()");
        appointmentService.addAppointment(appointment);
        System.out.println("✅ Gọi xong addAppointment()");

        return "redirect:/datlich/history";
    }

    // Hiển thị lịch sử đặt lịch
    @GetMapping("/history")
    public String getAppointmentHistory(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";

        List<Appointment> customerAppointments = new ArrayList<>();
        for (Appointment a : appointmentService.getAllAppointments()) {
            if (a.getCustomer() != null && a.getCustomer().getId().equals(customerId)) {
                customerAppointments.add(a);
            }
        }

        model.addAttribute("appointments", customerAppointments);
        return "history";
    }

    // Đánh dấu đã thanh toán
    @GetMapping("/pay/{id}")
    public String markAsPaid(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        appointment.setStatus("Paid");
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/datlich/history";
    }
}
