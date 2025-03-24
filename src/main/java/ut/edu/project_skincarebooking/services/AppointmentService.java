package ut.edu.project_skincarebooking.services;
import ut.edu.project_skincarebooking.models.Appointment;
import java.util.*;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    Appointment addAppointment(Appointment appointment);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
}
