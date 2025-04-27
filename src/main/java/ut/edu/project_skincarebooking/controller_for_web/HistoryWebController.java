package ut.edu.project_skincarebooking.controller_for_web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ut.edu.project_skincarebooking.models.Appointment;
import ut.edu.project_skincarebooking.services.interF.AppointmentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HistoryWebController {

    private final AppointmentService appointmentService;

    @GetMapping("/history")
    public String showHistory(Model model, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        List<Appointment> appointments = appointmentService.findAppointmentsByCustomerId(customerId);
        model.addAttribute("appointments", appointments);

        return "history";
    }

    @PostMapping("/history/delete/{id}")
    public String deleteAppointment(@PathVariable Long id, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment != null && appointment.getCustomer() != null && appointment.getCustomer().getId().equals(customerId)) {
            appointmentService.deleteAppointment(id);
        }

        return "redirect:/history";
    }
}
