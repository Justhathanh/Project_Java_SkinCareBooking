package ut.edu.project_skincarebooking.services.ImpleM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ut.edu.project_skincarebooking.models.Blog;
import ut.edu.project_skincarebooking.repositories.BlogReposiroty;
import ut.edu.project_skincarebooking.services.interF.BlogService;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogReposiroty blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with ID : " +id));
    }

    @Override
    public Blog getBlogByAuthor(String author) {
        return blogRepository.findAll().stream()//tao stream tu danh sach ban dau
                .filter(blog -> blog.getAuthor().equalsIgnoreCase(author)) //loc tac gia
                .findFirst() //blog dau tien thoa dieu kien
                .orElseThrow(() -> new RuntimeException("No blog found for author: " + author)); //khong tim thay tac gia
    }

    @Override
    public Blog createBlog(Blog blog) {
        blog.setCreatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog updatedBlog) {
        Blog blog = getBlogById(updatedBlog.getId());
        blog.setTitle(updatedBlog.getTitle());
        blog.setContent(updatedBlog.getContent());
        blog.setAuthor(updatedBlog.getAuthor());
        return blogRepository.save(blog);
    }
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

}
