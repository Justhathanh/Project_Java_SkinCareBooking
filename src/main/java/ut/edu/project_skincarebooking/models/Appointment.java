package ut.edu.project_skincarebooking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonBackReference(value = "customer-appointments")  // Thay vì @JsonManagedReference
    private Customer customer;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    @JsonBackReference(value = "therapist-appointments")  // Thay vì @JsonManagedReference
    private SkinTherapist therapist;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    @JsonBackReference(value = "service-appointments")
    private ServiceEntity service;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "appointment-feedback")
    private Feedback feedback;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("appointment")
    private Rating rating;

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
