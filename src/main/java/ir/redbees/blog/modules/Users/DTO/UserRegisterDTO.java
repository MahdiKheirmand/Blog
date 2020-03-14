package ir.redbees.blog.modules.Users.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.redbees.blog.enums.Roles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class UserRegisterDTO {
    private String name;
    private String email;
    private String password;

    private List<Roles> roles;

    @Transient
    @JsonIgnore
    private MultipartFile file;
}
