package ut.edu.project_skincarebooking.exceptions;
//loi khong tim duoc cuoc hen nao voi id
public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(Long id) {
        super("Appointment not found with ID: " + id);
    }
}
