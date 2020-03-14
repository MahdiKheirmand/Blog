package ir.redbees.blog.modules;


import ir.redbees.blog.modules.Posts.Blog.Blog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    private BlogService blogService;

    @Autowired
    public MainController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("blogs", blogService.findAllBlogsList());
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/403")
    public String accessDenied() {
        return "403";
    }
}
