package ut.edu.project_skincarebooking.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String serviceName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int duration; // Tính bằng phút

    @ManyToMany
    @JoinTable(
            name = "service_therapist",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "therapist_id")
    )
    private List<SkinTherapist> therapists;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;
    public Service(Long id, String serviceName, String description, double price, int duration) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }


}
