package ut.edu.project_skincarebooking.controller_for_web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.models.*;
import ut.edu.project_skincarebooking.services.interF.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceWebController {

    private final ServiceService serviceService;
    private final AppointmentService appointmentService;

    @GetMapping
    public String showServiceList(Model model) {
        List<ServiceEntity> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        return "service"; // Trang danh sách dịch vụ (service.html)
    }

    @GetMapping("/{id}/appointments")
    public String viewAppointmentsByService(@PathVariable("id") Long serviceId, Model model) {
        ServiceEntity service = serviceService.getServiceById(serviceId);
        if (service == null) {
            return "redirect:/service";
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByService(service);
        model.addAttribute("appointments", appointments);
        model.addAttribute("service", service);
        return "service_appointments"; // Trang mới
    }
}
