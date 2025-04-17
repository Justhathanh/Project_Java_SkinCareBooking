package ut.edu.project_skincarebooking.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceEntity {

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
    @JsonManagedReference(value = "service-appointments")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "service-feedbacks")
    private List<Feedback> feedbacks = new ArrayList<>();

    public ServiceEntity(Long id, String serviceName, String description, double price, int duration) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }


}
