package ut.edu.project_skincarebooking.services.interF;

import ut.edu.project_skincarebooking.models.News;
import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News getNewsById(Long id);
    News createNews(News news);
    void deleteNews(Long id);
    News upvoteNews(Long id);
    News downvoteNews(Long id);
    News updateNews(Long id, News updatedNews);
}
