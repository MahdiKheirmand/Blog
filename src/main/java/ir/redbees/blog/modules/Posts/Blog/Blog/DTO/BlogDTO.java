package ir.redbees.blog.modules.Posts.Blog.Blog.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.redbees.blog.modules.Posts.Blog.Categories.Entity.Category;
import ir.redbees.blog.modules.Users.Entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class BlogDTO {
    private long id;
    private String title;
    private String body;
    private User user;
    private String cover;
    private List<Category> categories;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
