package ut.edu.project_skincarebooking.models;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;


@Entity
@Table(name = "staffs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends Employee {
    @Column(nullable = false)
    private String department;

    public void handleCustomerQueries() {
        // TODO: Logic xử lý câu hỏi khách hàng
    }

    public void manageAppointments() {
        // TODO: Logic quản lý lịch hẹn
    }

    public void processPayments() {
        // TODO: Logic xử lý thanh toán
    }

    @Override
    public Map<String, String> viewSchedule() {
        return new HashMap<>();
    }
}

