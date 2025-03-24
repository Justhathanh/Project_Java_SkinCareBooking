package ut.edu.project_skincarebooking.exceptions;
//tim loi khong thay dịch vụ theo id

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String message) {
        super(message);
    }
}