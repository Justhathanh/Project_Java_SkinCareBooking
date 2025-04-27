package ut.edu.project_skincarebooking.services.ImpleM;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ut.edu.project_skincarebooking.models.Appointment;
import ut.edu.project_skincarebooking.models.ServiceEntity;
import ut.edu.project_skincarebooking.repositories.AppointmentRepository;
import ut.edu.project_skincarebooking.services.interF.AppointmentService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
        Appointment existing = getAppointmentById(id);
        if (existing == null) return null;

        existing.setDate(appointment.getDate());
        existing.setTime(appointment.getTime());
        existing.setTherapist(appointment.getTherapist());
        existing.setStatus(appointment.getStatus());
        existing.setServices(appointment.getServices());
        return appointmentRepository.save(existing);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> getAppointmentsByService(ServiceEntity service) {
        return appointmentRepository.findByServicesContaining(service);
    }

    @Override
    public List<Appointment> findAppointmentsByCustomerId(Long customerId) {
        return appointmentRepository.findAppointmentsByCustomerId(customerId);
    }

    @Override
    public Appointment getAppointmentWithServices(Long id) {
        return appointmentRepository.findByIdWithServices(id);
    }
}
