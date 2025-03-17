package ut.edu.project_skincarebooking.models;
import ut.edu.project_skincarebooking.models.Appointment;
import jakarta.persistence.*;
import lombok.*;
import ut.edu.project_skincarebooking.interfaces.IScheduleManagement;

import java.util.*;
@Entity
@Table(name = "skin_therapists")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkinTherapist extends Employee implements IScheduleManagement {
    @Column(nullable = false)
    private String specialization;
    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "therapist_schedule", joinColumns = @JoinColumn(name = "therapist_id"))
    @MapKeyColumn(name = "day_of_week")
    @Column(name = "working_hours", nullable = false)
    private Map<String, String> workSchedule;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;


    public List<String> getWorkSchedule() {
        return List.copyOf(workSchedule.values());
    }

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
    public List<String> viewSchedule() {
        return List.copyOf(workSchedule.values());
    }


    @Override
    public boolean updateSchedule() {
        // TODO: Logic cập nhật lịch trình
        return false;
    }

}
