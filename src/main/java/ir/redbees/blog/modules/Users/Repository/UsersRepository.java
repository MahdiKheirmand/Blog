package ir.redbees.blog.modules.Users.Repository;

import ir.redbees.blog.modules.Users.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {


    User findByEmail(String email);

    @Query("SELECT u from User u where u.email = mail")
    User userEmail(@Param("mail") String email);


}