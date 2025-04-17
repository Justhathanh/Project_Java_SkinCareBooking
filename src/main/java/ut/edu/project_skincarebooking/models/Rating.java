package ut.edu.project_skincarebooking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int score;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference(value = "customer-ratings")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    @JsonBackReference(value = "therapist-ratings")
    private SkinTherapist therapist;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
    @JsonIgnoreProperties("rating")
    private Appointment appointment;


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
