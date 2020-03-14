package ir.redbees.blog.modules.Users.Service;

import ir.redbees.blog.Config.MyBeanCopy;
import ir.redbees.blog.modules.Users.DTO.UserDTO;
import ir.redbees.blog.modules.Users.DTO.UserRegisterDTO;
import ir.redbees.blog.modules.Users.DTO.UserShowDTO;
import ir.redbees.blog.modules.Users.Entity.User;
import ir.redbees.blog.modules.Users.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UserService {
    private UsersRepository usersRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        modelMapper = new ModelMapper();
    }

    //ENTITY
    @Transactional
    public User registerUserEntity(User user) throws IOException, InvocationTargetException, IllegalAccessException {
        if (!user.getFile().isEmpty()) {
            String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
            byte[] bytes = user.getFile().getBytes();
            String fileName = UUID.randomUUID() + "." + Objects.requireNonNull(user.getFile().getContentType()).split("/")[1];
            Files.write(Paths.get(path + File.separator + fileName), bytes);
            user.setCover(fileName);
        }

        if (!user.getPassword().isEmpty()) {
                    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }

        if(user.getId() != null) {
            User exist = usersRepository.getOne(user.getId());
            MyBeanCopy myBeanCopy = new MyBeanCopy();
            myBeanCopy.copyProperties(exist,user);
            return usersRepository.save(exist);
        }

        return usersRepository.save(user);
    }

    public User findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public Page<User> findAllUsersPage(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public List<User> findAllUsersList() {
        return usersRepository.findAll();
    }

    public User findUserById(long id) {
        return usersRepository.getOne(id);
    }

    public void delete(long id) {
        usersRepository.deleteById(id);
    }



    //DTO
    @Transactional
    public UserShowDTO registerUserDTO(UserRegisterDTO dto) throws IOException {
        String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
        User user = modelMapper.map(dto, User.class);
        if (!dto.getFile().isEmpty()) {
            byte[] bytes = dto.getFile().getBytes();
            String fileName = UUID.randomUUID() + "." + Objects.requireNonNull(dto.getFile().getContentType()).split("/")[1];
            Files.write(Paths.get(path + File.separator + fileName), bytes);
            user.setCover(fileName);
        }
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        usersRepository.save(user);
        return modelMapper.map(user, UserShowDTO.class);
    }

    public List<UserShowDTO> findAllUsersDTO() {
        List<User> users = usersRepository.findAll();
        return Arrays.asList(modelMapper.map(users, UserShowDTO[].class));
    }

    public UserShowDTO findUserByIdDTO(long id) {
        User user = usersRepository.getOne(id);
        return modelMapper.map(user, UserShowDTO.class);

    }

}
