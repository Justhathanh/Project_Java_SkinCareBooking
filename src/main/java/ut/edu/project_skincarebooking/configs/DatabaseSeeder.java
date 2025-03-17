package ut.edu.project_skincarebooking.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ut.edu.project_skincarebooking.models.*;
import ut.edu.project_skincarebooking.repositories.*;

import java.util.List;
import java.util.Map;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final SkinTherapistRepository skinTherapistRepository;

    public DatabaseSeeder(UserRepository userRepository,
                          ServiceRepository serviceRepository,
                          SkinTherapistRepository skinTherapistRepository) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.skinTherapistRepository = skinTherapistRepository;
    }

    @Override
    public void run(String... args) {
        seedAdminAccount();
        seedServices();
        seedSkinTherapists();
    }

    private void seedAdminAccount() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User(null, "admin", "password123");
            userRepository.save(admin);
            System.out.println("✅ Admin account created: " + admin);
        }
    }

    private void seedServices() {
        if (serviceRepository.count() == 0) {
            List<Service> services = List.of(
                    new Service(null, "Facial Treatment", "Chăm sóc da mặt", 50.0),
                    new Service(null, "Acne Removal", "Điều trị mụn", 60.0),
                    new Service(null, "Anti-Aging", "Chống lão hóa", 80.0)
            );
            serviceRepository.saveAll(services);
            System.out.println("✅ Services inserted");
        }
    }

    private void seedSkinTherapists() {
        if (skinTherapistRepository.count() == 0) {
            SkinTherapist therapist = SkinTherapist.builder()
                    .name("Dr. Tran")
                    .specialization("Acne Treatment")
                    .workSchedule(Map.of("Monday", "09:00-17:00", "Wednesday", "10:00-18:00"))
                    .build();
            skinTherapistRepository.save(therapist);
            System.out.println("✅ Skin Therapist created: " + therapist);
        }
    }
}
