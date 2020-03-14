package ir.redbees.blog.modules.Posts.Blog.Blog.Repository;

import ir.redbees.blog.modules.Posts.Blog.Blog.Entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

//    @Query("select  b from Blog b where :#{#blog.title} is null or b.title like concat('%', :#{#blog.title},'%')")
    /*@Query("select b from Blog b join b.categories bc where " +
            "(coalesce(:#{#blog.categories},null) is null or " +
            "bc in (:#{#blog.categories})) " +
            "group by b.id having count (b.id) >= :num")*/
    @Query("select b from Blog b join b.categories bc where (:#{#blog.title} is null or " +
        "b.title like concat('%',:#{#blog.title},'%')) and " +
        "(coalesce(:#{#blog.categories},null) is null or " +
        "bc in (:#{#blog.categories})) " +
        "group by b.id having count (b.id) >= :num")
    Page<Blog> findBySearch(Blog blog, @Param("num") Long size, Pageable pageable);

    @Query("select b from Blog b join b.categories bc where (:#{#blog.title} is null or " +
            "b.title like concat('%',:#{#blog.title},'%')) and " +
            "(coalesce(:#{#blog.categories},null) is null or " +
            "bc in (:#{#blog.categories})) " +
            "group by b.id having count (b.id) >= :num")
    List<Blog> findBySearchRest(Blog blog, @Param("num") Long size);

//    List<Blog> findByTitleContaining(String title);
}
