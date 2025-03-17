package ut.edu.project_skincarebooking.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ut.edu.project_skincarebooking.models.User;
import ut.edu.project_skincarebooking.repositories.UserRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;

    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User user = new User(null, "admin", "password123");
            userRepository.save(user);
            System.out.println("✅ Data inserted: " + user);
        }
    }
}