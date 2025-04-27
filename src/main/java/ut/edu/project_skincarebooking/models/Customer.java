package ut.edu.project_skincarebooking.models;

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

    @Column(nullable = true)
    private String dateOfBirth;

    @Column(nullable = false)
    private int loyaltyPoints;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "customer-appointments")
    private List<Appointment> appointments;





    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "customer-feedbacks")
    private List<Feedback> feedbacks = new ArrayList<>();

    // Add a one-to-one relationship with User
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public void logout() {

    }

    @Column(nullable = true, unique = false)
    public String phone;

    @Override
    public void updateProfile() {

    }
}