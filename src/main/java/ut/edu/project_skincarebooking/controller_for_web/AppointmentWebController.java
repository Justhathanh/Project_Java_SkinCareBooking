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
    public String createAppointment(@RequestParam("service") Long serviceId,
                                    @RequestParam("specialist") String specialistCode,
                                    @RequestParam("date") String dateStr,
                                    @RequestParam("time") String timeStr,
                                    HttpSession session) {

        // ✅ Lấy customer từ session
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";

        Customer customer = customerService.getCustomerById(customerId);

        // ✅ Lấy dịch vụ
        ServiceEntity selectedService = serviceService.getServiceById(serviceId);
        if (selectedService == null) return "redirect:/datlich";

        // ✅ Lấy chuyên viên hoặc random nếu không chọn
        SkinTherapist therapist;
        if ("khong_chon_chuyen_vien".equals(specialistCode) || specialistCode.isEmpty()) {
            List<SkinTherapist> allTherapists = therapistService.getAllTherapists();
            therapist = allTherapists.get(new Random().nextInt(allTherapists.size()));
        } else {
            Long therapistId = Long.parseLong(specialistCode.replace("no", ""));
            therapist = therapistService.getTherapistById(therapistId);
        }

        // ✅ Tạo Appointment
        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setService(selectedService);
        appointment.setTherapist(therapist);
        appointment.setDate(LocalDate.parse(dateStr));
        appointment.setTime(LocalTime.parse(timeStr));
        appointment.setStatus("Unpaid");

        appointmentService.addAppointment(appointment);

        return "redirect:/index?bookingSuccess=true";
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
