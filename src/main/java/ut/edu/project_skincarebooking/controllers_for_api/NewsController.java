package ut.edu.project_skincarebooking.controllers_for_api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.models.News;
import ut.edu.project_skincarebooking.services.interF.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news) {
        return ResponseEntity.ok(newsService.createNews(news));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<News> upvoteNews(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.upvoteNews(id));
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity<News> downvoteNews(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.downvoteNews(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News updatedNews) {
        News news = newsService.updateNews(id, updatedNews);
        return ResponseEntity.ok(news);
    }

}
