package ut.edu.project_skincarebooking.services.ImpleM;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ut.edu.project_skincarebooking.exceptions.AppointmentNotFoundException;
import ut.edu.project_skincarebooking.models.Appointment;
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
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + id));
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = getAppointmentById(id);
        existingAppointment.setDate(updatedAppointment.getDate());
        existingAppointment.setTime(updatedAppointment.getTime());
        existingAppointment.setCustomer(updatedAppointment.getCustomer());
        existingAppointment.setService(updatedAppointment.getService());
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
    }



}
