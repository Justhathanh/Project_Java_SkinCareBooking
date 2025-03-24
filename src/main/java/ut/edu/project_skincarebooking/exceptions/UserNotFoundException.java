package ut.edu.project_skincarebooking.exceptions;
// file nay de tim loi khong tim thay user
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}