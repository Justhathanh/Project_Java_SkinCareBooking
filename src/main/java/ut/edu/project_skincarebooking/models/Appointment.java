package ut.edu.project_skincarebooking.models;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "therapist_id", nullable = false)
    private SkinTherapist therapist;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity serviceEntity;


    public void cancelAppointment() {
        // TODO: Logic hủy cuộc hẹn
    }

    public void confirmAppointment() {
        // TODO: Logic xác nhận cuộc hẹn
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}
