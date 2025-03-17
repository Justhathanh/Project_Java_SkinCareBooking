package ut.edu.project_skincarebooking.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "therapist_id", nullable = false)
    private SkinTherapist therapist;

    @Temporal(TemporalType.DATE)
    private Date workDate;

    @Column(nullable = false)
    private String shift; // Ví dụ: "Morning", "Afternoon", "Evening"
}
