package ut.edu.project_skincarebooking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL)
    @JsonBackReference(value = "therapist-appointments")
    private List<Appointment> appointments;


    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "therapist-feedbacks")
    private List<Feedback> feedbacks = new ArrayList<>();


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
