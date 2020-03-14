package ir.redbees.blog.modules.Posts.Blog.Categories.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.redbees.blog.modules.Posts.Blog.Blog.Entity.Blog;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class CategoryDTO {
    private long id;
    private String name;
    private List<Blog> blogs;

}
