package ut.edu.project_skincarebooking.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ut.edu.project_skincarebooking.interfaces.IScheduleManagement;

import java.util.*;

@Entity
@Table(name = "skin_therapists")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SkinTherapist extends Employee implements IScheduleManagement {
    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "therapist_schedule", joinColumns = @JoinColumn(name = "therapist_id"))
    @MapKeyColumn(name = "day_of_week")
    @Column(name = "working_hours", nullable = false)
    private Map<String, String> workSchedule = new HashMap<>();

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules = new ArrayList<>();

    public void performService() {
        // TODO: Logic thực hiện dịch vụ
    }

    public void updateResult() {
        // TODO: Logic cập nhật kết quả
    }

    public List<Appointment> viewAppointments() {
        return appointments;
    }

    @Override
    public Map<String, String> viewSchedule() {
        return workSchedule;
    }

    @Override
    public boolean updateSchedule() {
        // TODO: Logic cập nhật lịch trình
        return false;
    }
}
