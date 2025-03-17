package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.project_skincarebooking.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
