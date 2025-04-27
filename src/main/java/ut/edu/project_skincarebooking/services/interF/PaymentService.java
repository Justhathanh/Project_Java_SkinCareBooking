package ut.edu.project_skincarebooking.services.interF;

import ut.edu.project_skincarebooking.models.Payment;

public interface PaymentService {
    Payment createPayment(Payment payment);

    Payment getPaymentByAppointmentId(Long appointmentId);

    void markAsCompleted(Long paymentId);

    void markAsPending(Long paymentId);
}
