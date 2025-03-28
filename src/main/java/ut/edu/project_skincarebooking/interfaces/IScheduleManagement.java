package ut.edu.project_skincarebooking.interfaces;
import ut.edu.project_skincarebooking.models.Appointment;
import java.util.*;


public interface IScheduleManagement {
    Map<String, String> viewSchedule();
    boolean updateSchedule();
    List<Appointment> viewAppointments();
}
