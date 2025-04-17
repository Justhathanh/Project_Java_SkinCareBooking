package ut.edu.project_skincarebooking.services.interF;
import ut.edu.project_skincarebooking.models.Blog;

import java.util.*;

public interface BlogService {
    List<Blog> getAllBlogs();
    Blog getBlogById(Long id);
    Blog getBlogByAuthor(String author);
    Blog createBlog(Blog blog);
    Blog updateBlog(Blog blog);
    void deleteBlog(Long id);

}
