package ir.redbees.blog.modules.Posts.Blog.Blog.Controller;


import ir.redbees.blog.modules.Posts.Blog.Blog.DTO.BlogDTO;
import ir.redbees.blog.modules.Posts.Blog.Blog.DTO.BlogRegisterDTO;
import ir.redbees.blog.modules.Posts.Blog.Blog.Entity.Blog;
import ir.redbees.blog.modules.Posts.Blog.Blog.Service.BlogService;
import ir.redbees.blog.modules.Posts.Blog.Categories.Service.CategoryService;
import ir.redbees.blog.modules.Users.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    private BlogService blogService;
    private CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public BlogController(BlogService blogService, CategoryService categoryService, UserService userService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerBlog(Model model) throws IOException {
        model.addAttribute("blog",new Blog());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "blogs/registerBlog";
    }

    @PostMapping("/register")
    public String registerBlog(@ModelAttribute Blog blog, Principal principal) throws IOException, InvocationTargetException, IllegalAccessException {
        blog.setUser(userService.findByEmail(principal.getName()));
        blogService.registerBlogEntity(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/edit/{id}")
    public String registerBlog(Model model,@PathVariable("id") long id) throws IOException {
        model.addAttribute("blog",blogService.getBlog(id));
        model.addAttribute("categories", categoryService.findAllCategories());
        return "blogs/registerBlog";
    }

    @GetMapping("/delete/{id}")
    public String registerBlog(@PathVariable("id") long id) throws IOException {
        blogService.deleteBlog(id);
        return "redirect:/blogs";
    }


    @GetMapping({"","/"})
    public String showRegisterPosts(Model model, @PageableDefault(size = 5)Pageable pageable) {
        model.addAttribute("blogs",blogService.findAllBlogsPage(pageable));
        return "blogs/blogs";
    }

    @GetMapping("/{id}")
    public String showBlog(Model model,@PathVariable long id){
        model.addAttribute("blog",blogService.getBlog(id));
        return "blogs/blog";
    }

    /*@GetMapping({"","/"})
    public String showRegisterPosts(Model model) {
        model.addAttribute("blogs",blogService.findAllBlogsList());
        return "blogs/blogs";
    }*/




    //DTO





    //REST
    //ENTITY
    @PostMapping("/rest")
    public @ResponseBody
    Blog registerBlogRest(@ModelAttribute BlogRegisterDTO dto) throws IOException {
        return blogService.registerBlogDTO(dto);
    }

    @GetMapping("/rest")
    public @ResponseBody List<Blog> getAllBlogRest(){
        return blogService.findAllBlogsList();
    }

    @GetMapping("/rest/{id}")
    public @ResponseBody Blog getBlogRest(@PathVariable long id){
        return blogService.getBlog(id);
    }

    @DeleteMapping("/rest/{id}")
    public @ResponseBody void deleteBlogRest(@PathVariable long id){
        blogService.deleteBlog(id);
    }

    @GetMapping("/rest/search")
    public @ResponseBody List<Blog> searchRest(@ModelAttribute Blog blog){
        return blogService.findBySearchRest(blog);
    }

    @GetMapping("/search")
    public @ResponseBody
    Page<Blog> search(@ModelAttribute Blog blog,Pageable pageable){
        return blogService.findBySearch(blog,pageable);
    }


    //REST
    //DTO
    /*@PostMapping("/rest/dto")
    public @ResponseBody BlogDTO registerBlogDTORest(@RequestBody Blog blog){
        return blogService.registerBlogDTO(blog);
    }*/

    @GetMapping("/rest/dto")
    public @ResponseBody
    List<BlogDTO> findAllBlogDTORest(){
        return blogService.findAllBlogDTO();
    }

    @GetMapping("/rest/dto/{id}")
    public @ResponseBody BlogDTO findBlogDTORest(@PathVariable long id){
        return blogService.findBlogDTO(id);
    }


}
