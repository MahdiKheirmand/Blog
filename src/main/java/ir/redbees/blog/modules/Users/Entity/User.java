package ir.redbees.blog.modules.Users.Entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.redbees.blog.enums.Roles;
import ir.redbees.blog.modules.Posts.Blog.Blog.Entity.Blog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users_tbl")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@JoinColumn(unique = true)
    private String username;*/

    @Column(unique = true)
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

//    @JsonIgnore
    @NotBlank
    private String password;
    private String cover;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    private boolean enabled = true;

    @NotEmpty
    @ElementCollection(targetClass = Roles.class)
    @CollectionTable(name = "Authorities", joinColumns =
    @JoinColumn(name = "email",referencedColumnName = "email"))
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs;

    public User() {
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String name, String password, String cover, List<Roles> roles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.cover = cover;
        this.roles = roles;
    }
}
