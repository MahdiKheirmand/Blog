package ir.redbees.blog.modules.Posts.Blog.Categories.Service;


import ir.redbees.blog.modules.Posts.Blog.Categories.DTO.CategoryDTO;
import ir.redbees.blog.modules.Posts.Blog.Categories.Entity.Category;
import ir.redbees.blog.modules.Posts.Blog.Categories.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        modelMapper = new ModelMapper();
    }

    public Category registerCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategory(long id) {
        return categoryRepository.getOne(id);
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }


    //DTO

    /*public CategoryDTO registerCategoryDTO(Category category) {
        this.categoryRepository.save(category);
        return modelMapper.map(category,CategoryDTO.class);
    }*/

    public List<CategoryDTO> findAllCategoriesDTO() {
        List<Category> categories = categoryRepository.findAll();
        return Arrays.asList(modelMapper.map(categories, CategoryDTO[].class));
    }

    public CategoryDTO findCategoryDTO(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return modelMapper.map(category, CategoryDTO.class);
        } else {
            return null;
        }
    }
}
