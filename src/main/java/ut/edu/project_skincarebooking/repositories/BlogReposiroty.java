package ut.edu.project_skincarebooking.repositories;
import ut.edu.project_skincarebooking.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogReposiroty extends JpaRepository<Blog, Long> {
}
