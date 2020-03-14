package ir.redbees.blog.modules.Posts.Blog.Categories.Controller;

import ir.redbees.blog.modules.Posts.Blog.Categories.DTO.CategoryDTO;
import ir.redbees.blog.modules.Posts.Blog.Categories.Entity.Category;
import ir.redbees.blog.modules.Posts.Blog.Categories.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/register")
    public String registerCategoryGet(Model model) throws IOException {
        model.addAttribute("category", new Category());
        return "categories/registerCategory";
    }

    @PostMapping("/register")
    public String registerCategoryPost(@ModelAttribute @Valid Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "categories/registerCategory";
        } 
        categoryService.registerCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryGet(Model model,@PathVariable("id") Long id) throws IOException {
        model.addAttribute("category",categoryService.findCategory(id));
        return "categories/registerCategory";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategoryGet(Model model,@PathVariable("id") Long id) throws IOException {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping({"","/"})
    public String showRegisterPosts(Model model) {
        model.addAttribute("categories",categoryService.findAllCategories());
        return "categories/categories";
    }

    //REST
    @PostMapping("/rest")
    public @ResponseBody
    Category registerCategory(@RequestBody Category category) {
        return categoryService.registerCategory(category);
    }

    @GetMapping("/rest")
    public @ResponseBody
    List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/rest/{id}")
    public @ResponseBody
    Category findCategory(@PathVariable long id) {
        return categoryService.findCategory(id);
    }

    @DeleteMapping("/rest/{id}")
    public @ResponseBody
    void deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
    }

    //REST
    //DTO
    /*@PostMapping("/rest/dto")
    public @ResponseBody
    CategoryDTO registerCategoryDTO(@RequestBody Category category) {
        return categoryService.registerCategoryDTO(category);
    }*/

    @GetMapping("/rest/dto")
    public @ResponseBody
    List<CategoryDTO> findAllCategoriesDTO() {
        return categoryService.findAllCategoriesDTO();
    }

    @GetMapping("/rest/dto/{id}")
    public @ResponseBody
    CategoryDTO findCategoryDTO(@PathVariable long id) {
        return categoryService.findCategoryDTO(id);
    }


}
