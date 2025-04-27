package ut.edu.project_skincarebooking.services.ImpleM;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ut.edu.project_skincarebooking.models.Appointment;
import ut.edu.project_skincarebooking.models.Payment;
import ut.edu.project_skincarebooking.repositories.AppointmentRepository;
import ut.edu.project_skincarebooking.repositories.PaymentRepository;
import ut.edu.project_skincarebooking.services.interF.PaymentService;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentByAppointmentId(Long appointmentId) {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getAppointment().getId().equals(appointmentId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void markAsCompleted(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow();
        payment.setStatus("Completed");
        paymentRepository.save(payment);

        Appointment appointment = payment.getAppointment();
        appointment.setStatus("Paid");
        appointmentRepository.save(appointment);
    }

    @Override
    public void markAsPending(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow();
        payment.setStatus("Pending");
        paymentRepository.save(payment);
    }
}
