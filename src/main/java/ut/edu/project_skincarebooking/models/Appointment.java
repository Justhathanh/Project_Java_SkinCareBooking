package ut.edu.project_skincarebooking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference(value = "customer-appointments")
    private Customer customer;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    @JsonManagedReference(value = "therapist-appointments")
    private SkinTherapist therapist;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    @JsonBackReference(value = "service-appointments")
    private ServiceEntity service;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "appointment-feedback")
    private Feedback feedback;
    // Logic hủy cuộc hẹn
    public void cancelAppointment() {
        this.status = "Cancelled";
    }

    // Logic xác nhận cuộc hẹn
    public void confirmAppointment() {
        this.status = "Confirmed";
    }

    // Cập nhật trạng thái cuộc hẹn
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}
