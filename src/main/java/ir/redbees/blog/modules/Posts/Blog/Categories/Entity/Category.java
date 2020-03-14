package ir.redbees.blog.modules.Posts.Blog.Categories.Entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.redbees.blog.modules.Posts.Blog.Blog.Entity.Blog;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "category_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "name")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "NOT BLANK")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Blog> blogs;


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
