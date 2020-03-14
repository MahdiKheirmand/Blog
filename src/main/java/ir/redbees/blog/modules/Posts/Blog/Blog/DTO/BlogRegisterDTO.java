package ir.redbees.blog.modules.Posts.Blog.Blog.DTO;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.redbees.blog.modules.Posts.Blog.Categories.Entity.Category;
import ir.redbees.blog.modules.Users.Entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class BlogRegisterDTO {
    private String title;
    private String body;
    @Transient
    @JsonIgnore
    private MultipartFile file;
    private User user;
    private List<Category> categories;
}
