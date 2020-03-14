package ir.redbees.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BlogNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogNewsApplication.class, args);
    }

}
