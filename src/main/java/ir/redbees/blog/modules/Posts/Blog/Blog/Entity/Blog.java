package ir.redbees.blog.modules.Posts.Blog.Blog.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.redbees.blog.modules.Posts.Blog.Categories.Entity.Category;
import ir.redbees.blog.modules.Users.Entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;
    private String cover;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;

    @ManyToMany
    @JoinTable(name = "blog_category")
    private List<Category> categories;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Transient
    @JsonIgnore
    private MultipartFile file;


    public Blog(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Blog(String title, String body, String cover) {
        this.title = title;
        this.body = body;
        this.cover = cover;
    }

    public Blog() {
    }


}
