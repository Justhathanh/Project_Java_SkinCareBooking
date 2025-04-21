package ut.edu.project_skincarebooking.services.interF;

import ut.edu.project_skincarebooking.models.Customer;
import ut.edu.project_skincarebooking.models.User;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerByUser(User user);

    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
}
