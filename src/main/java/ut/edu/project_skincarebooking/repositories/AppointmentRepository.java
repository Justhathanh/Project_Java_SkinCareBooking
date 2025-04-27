package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ut.edu.project_skincarebooking.models.Appointment;
import ut.edu.project_skincarebooking.models.ServiceEntity;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.services WHERE a.customer.id = :customerId")
    List<Appointment> findAppointmentsByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.services WHERE a.id = :id")
    Appointment findByIdWithServices(@Param("id") Long id);

    List<Appointment> findByServicesContaining(ServiceEntity service);
}
