package ut.edu.project_skincarebooking.services.interF;
import ut.edu.project_skincarebooking.models.User;
import java.util.*;

public interface UserService {
    List<User> getAllUsers(); //lay tat ca user
    Optional<User> getUserById(Long id); //tim kiem user bang id
    User createUser(User user); //tao nguoi dung moi
    User updateUser(Long id,User user); //cap nhat thong tin nguoi dung
    void deleteUser(Long id);//xoa nguoi dung theo ID
}
