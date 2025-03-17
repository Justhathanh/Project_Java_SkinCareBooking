package ut.edu.project_skincarebooking.interfaces;

import ut.edu.project_skincarebooking.models.Appointment;

import java.util.List;

public interface IScheduleManagement {
    List<String> viewSchedule();
    boolean updateSchedule();
    List<Appointment> viewAppointments();
}
