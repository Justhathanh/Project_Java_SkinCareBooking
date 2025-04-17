// BlogController.java
package ut.edu.project_skincarebooking.controllers_for_api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.models.Blog;
import ut.edu.project_skincarebooking.services.interF.BlogService;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<Blog> getBlogByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(blogService.getBlogByAuthor(author));
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.createBlog(blog));
    }

    @PutMapping
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.updateBlog(blog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok("Blog deleted successfully.");
    }
}
