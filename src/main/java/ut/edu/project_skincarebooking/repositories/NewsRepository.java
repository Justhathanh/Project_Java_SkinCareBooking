package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.project_skincarebooking.models.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
