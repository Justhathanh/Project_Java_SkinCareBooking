package ut.edu.project_skincarebooking.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ut.edu.project_skincarebooking.interfaces.IAccount;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person implements IAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private int loyaltyPoints;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference(value = "customer-appointments")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "customer-feedbacks")
    private List<Feedback> feedbacks = new ArrayList<>();


    @Override
    public boolean login() {
        return false;
    }

    @Override
    public void logout() {

    }
    @Column(nullable = false, unique = true)
    private String phone;

    @Override
    public void updateProfile() {

    }
}
