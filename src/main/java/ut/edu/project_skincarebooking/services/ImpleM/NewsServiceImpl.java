package ut.edu.project_skincarebooking.services.ImpleM;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ut.edu.project_skincarebooking.exceptions.NewsNotFoundException;
import ut.edu.project_skincarebooking.models.News;
import ut.edu.project_skincarebooking.repositories.NewsRepository;
import ut.edu.project_skincarebooking.services.interF.NewsService;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElseThrow();
    }

    @Override
    public News createNews(News news) {
        news.setPostedAt(LocalDateTime.now());
        return newsRepository.save(news);
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public News upvoteNews(Long id) {
        News news = getNewsById(id);
        news.upvote();
        return newsRepository.save(news);
    }

    @Override
    public News downvoteNews(Long id) {
        News news = getNewsById(id);
        news.downvote();
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(Long id, News updatedNews) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("News not found with ID: " + id));

        existingNews.setTitle(updatedNews.getTitle());
        existingNews.setContent(updatedNews.getContent());
        existingNews.setAuthor(updatedNews.getAuthor());
        existingNews.setPostedAt(updatedNews.getPostedAt());

        return newsRepository.save(existingNews);
    }

}
