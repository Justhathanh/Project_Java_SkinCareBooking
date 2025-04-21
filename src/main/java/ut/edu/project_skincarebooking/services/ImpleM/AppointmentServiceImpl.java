package ut.edu.project_skincarebooking.services.ImpleM;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ut.edu.project_skincarebooking.exceptions.AppointmentNotFoundException;
import ut.edu.project_skincarebooking.models.Appointment;
import ut.edu.project_skincarebooking.repositories.AppointmentRepository;
import ut.edu.project_skincarebooking.services.interF.AppointmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> getAllAppointments() {
        logger.info("Fetching all appointments from database");
        return appointmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Appointment getAppointmentById(Long id) {
        logger.info("Fetching appointment with ID: {}", id);
        return appointmentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with ID: " + id);
                });
    }

    @Override
    @Transactional
    public Appointment addAppointment(Appointment appointment) {
        if (appointment == null) {
            logger.error("Cannot save null appointment");
            throw new IllegalArgumentException("Appointment cannot be null");
        }

        if (appointment.getCustomer() == null) {
            logger.error("Cannot save appointment with null customer");
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (appointment.getTherapist() == null) {
            logger.error("Cannot save appointment with null therapist");
            throw new IllegalArgumentException("Therapist cannot be null");
        }

        if (appointment.getService() == null) {
            logger.error("Cannot save appointment with null service");
            throw new IllegalArgumentException("Service cannot be null");
        }

        logger.info("Saving new appointment for customer: {}, therapist: {}, service: {}",
                appointment.getCustomer().getId(),
                appointment.getTherapist().getId(),
                appointment.getService().getId());

        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        logger.info("Updating appointment with ID: {}", id);

        Appointment existingAppointment = getAppointmentById(id);

        if (updatedAppointment.getDate() != null) {
            existingAppointment.setDate(updatedAppointment.getDate());
        }

        if (updatedAppointment.getTime() != null) {
            existingAppointment.setTime(updatedAppointment.getTime());
        }

        if (updatedAppointment.getCustomer() != null) {
            existingAppointment.setCustomer(updatedAppointment.getCustomer());
        }

        if (updatedAppointment.getTherapist() != null) {
            existingAppointment.setTherapist(updatedAppointment.getTherapist());
        }

        if (updatedAppointment.getService() != null) {
            existingAppointment.setService(updatedAppointment.getService());
        }

        if (updatedAppointment.getStatus() != null && !updatedAppointment.getStatus().isEmpty()) {
            existingAppointment.setStatus(updatedAppointment.getStatus());
        }

        logger.info("Saving updated appointment");
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    @Transactional
    public void deleteAppointment(Long id) {
        logger.info("Deleting appointment with ID: {}", id);
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
        logger.info("Appointment deleted successfully");
    }
}