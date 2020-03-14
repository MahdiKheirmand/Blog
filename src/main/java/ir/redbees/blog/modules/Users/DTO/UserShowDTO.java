package ir.redbees.blog.modules.Users.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.redbees.blog.enums.Roles;
import ir.redbees.blog.modules.Posts.Blog.Blog.Entity.Blog;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class UserShowDTO {
    private long id;
    private String name;
    private String email;
    private String cover;
    private List<Blog> blogs;
    private List<Roles> roles;
}
