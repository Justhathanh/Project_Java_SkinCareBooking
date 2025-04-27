package ut.edu.project_skincarebooking.controller_for_web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.models.Appointment;
import ut.edu.project_skincarebooking.models.Payment;
import ut.edu.project_skincarebooking.services.interF.AppointmentService;
import ut.edu.project_skincarebooking.services.interF.PaymentService;

import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentWebController {

    private final AppointmentService appointmentService;
    private final PaymentService paymentService;

    @GetMapping("/detail/{appointmentId}")
    public String showPaymentPage(@PathVariable Long appointmentId, Model model) {
        Appointment appointment = appointmentService.getAppointmentWithServices(appointmentId); // ✅ Dùng method mới
        Payment payment = paymentService.getPaymentByAppointmentId(appointmentId);
        boolean isPaid = payment != null && "Paid".equalsIgnoreCase(payment.getStatus());

        double totalAmount = appointment.getServices().stream()
                .mapToDouble(s -> s.getPrice() == null ? 0.0 : s.getPrice())
                .sum();

        model.addAttribute("appointment", appointment);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("payment", payment != null ? payment : new Payment());
        model.addAttribute("isPaid", isPaid);
        return "payment";
    }

    @PostMapping("/confirm")
    public String confirmPayment(@RequestParam("appointmentId") Long appointmentId,
                                 @RequestParam("paymentMethod") String paymentMethod,
                                 HttpSession session) {
        Appointment appointment = appointmentService.getAppointmentWithServices(appointmentId); // ✅
        double totalAmount = appointment.getServices().stream()
                .mapToDouble(s -> s.getPrice() != null ? s.getPrice() : 0.0)
                .sum();

        Payment payment = Payment.builder()
                .appointment(appointment)
                .amount(totalAmount)
                .paymentDate(new Date())
                .status("Paid")
                .paymentMethod(paymentMethod)
                .build();

        paymentService.createPayment(payment);
        appointment.setStatus("Paid");
        appointmentService.updateAppointment(appointmentId, appointment);

        return "redirect:/history";
    }
}
