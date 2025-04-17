package ut.edu.project_skincarebooking.exceptions;
//tao constructor
//loi khong tim duoc cuoc hen nao voi id
public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
