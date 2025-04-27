package ut.edu.project_skincarebooking.services.interF;

import ut.edu.project_skincarebooking.models.Appointment;
import ut.edu.project_skincarebooking.models.ServiceEntity;
import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    Appointment addAppointment(Appointment appointment);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
    List<Appointment> getAppointmentsByService(ServiceEntity service);
    List<Appointment> findAppointmentsByCustomerId(Long customerId);

    Appointment getAppointmentWithServices(Long id); // ✅ Thêm
}
