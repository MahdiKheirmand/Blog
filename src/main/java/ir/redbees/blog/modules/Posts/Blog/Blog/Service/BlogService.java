package ir.redbees.blog.modules.Posts.Blog.Blog.Service;

import ir.redbees.blog.Config.MyBeanCopy;
import ir.redbees.blog.modules.Posts.Blog.Blog.DTO.BlogDTO;
import ir.redbees.blog.modules.Posts.Blog.Blog.DTO.BlogRegisterDTO;
import ir.redbees.blog.modules.Posts.Blog.Blog.Entity.Blog;
import ir.redbees.blog.modules.Posts.Blog.Blog.Repository.BlogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class BlogService {
    private BlogRepository blogRepository;
    private ModelMapper modelMapper;


    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
        modelMapper = new ModelMapper();
    }


    @Transactional
    public Blog registerBlogEntity(Blog blog) throws IOException, InvocationTargetException, IllegalAccessException {
        if (!blog.getFile().isEmpty()) {
            String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
            byte[] bytes = blog.getFile().getBytes();
            String name = UUID.randomUUID() + "." + Objects.requireNonNull(blog.getFile().getContentType()).split("/")[1];
            Files.write(Paths.get(path + File.separator + name), bytes);
            blog.setCover(name);
        }

        if (blog.getId() != null) {
            Blog exist = blogRepository.getOne(blog.getId());
            MyBeanCopy myBeanCopy = new MyBeanCopy();
            myBeanCopy.copyProperties(exist,blog);
            return blogRepository.save(exist);
        }

        return this.blogRepository.save(blog);
    }

    public List<Blog> findAllBlogsList() {
        return blogRepository.findAll();
    }

    public Page<Blog> findAllBlogsPage(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    public Blog getBlog(long id) {
        return blogRepository.getOne(id);
    }

    public void deleteBlog(long id){
        blogRepository.deleteById(id);
    }

    public Page<Blog> findBySearch(Blog blog,Pageable pageable) {
        return blogRepository.findBySearch(blog, (blog.getCategories()!= null ? (long) blog.getCategories().size() : 0),pageable);
    }

    public List<Blog> findBySearchRest(Blog blog) {
        return blogRepository.findBySearchRest(blog, (blog.getCategories()!= null ? (long) blog.getCategories().size() : 0));
    }




    //DTO
    @Transactional
    public Blog registerBlogDTO(BlogRegisterDTO dto) throws IOException {
        Blog blog = modelMapper.map(dto,Blog.class);
        if(!dto.getFile().isEmpty()) {
            String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
            byte[] bytes = dto.getFile().getBytes();
            String fileName = UUID.randomUUID() + "." + Objects.requireNonNull(dto.getFile().getContentType()).split("/")[1];
            Files.write(Paths.get(path + File.separator + fileName), bytes);
            blog.setCover(fileName);
        }
        blogRepository.save(blog);
        return blog;
    }

    public List<BlogDTO> findAllBlogDTO() {
        List<Blog> blogs = blogRepository.findAll();
        return Arrays.asList(modelMapper.map(blogs, BlogDTO[].class));
    }

    public BlogDTO findBlogDTO(long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if(blog.isPresent()){
            return modelMapper.map(blog, BlogDTO.class);
        } else {
            return null;
        }
    }






}
