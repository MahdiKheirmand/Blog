package ir.redbees.blog.modules.Posts.Blog.Categories.Repository;


import ir.redbees.blog.modules.Posts.Blog.Categories.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
