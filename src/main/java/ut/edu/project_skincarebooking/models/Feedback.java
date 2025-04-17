package ut.edu.project_skincarebooking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    @JsonBackReference(value = "appointment-feedback")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference(value = "customer-feedbacks")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    @JsonBackReference(value = "therapist-feedbacks")
    private SkinTherapist therapist;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonBackReference(value = "service-feedbacks")
    private ServiceEntity service;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
