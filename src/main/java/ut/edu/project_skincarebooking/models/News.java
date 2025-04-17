package ut.edu.project_skincarebooking.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private LocalDateTime postedAt;

    private int upvotes = 0;
    private int downvotes = 0;

    public void upvote() {
        this.upvotes++;
    }

    public void downvote() {
        this.downvotes++;
    }
}
