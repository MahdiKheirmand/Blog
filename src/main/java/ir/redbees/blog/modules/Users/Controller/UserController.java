package ir.redbees.blog.modules.Users.Controller;

import ir.redbees.blog.modules.Users.DTO.UserRegisterDTO;
import ir.redbees.blog.modules.Users.DTO.UserShowDTO;
import ir.redbees.blog.modules.Users.Entity.User;
import ir.redbees.blog.modules.Users.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //ENTITY
    @GetMapping("/register")
    public String registerUserGetEntity(Model model) throws IOException {
        model.addAttribute("user", new User());
        return "users/registerUser";
    }

    @PostMapping("/register")
    public String registerUserPostEntity(@ModelAttribute(name = "user") @Valid User user, BindingResult bindingResult)
            throws IOException, InvocationTargetException, IllegalAccessException {
        if (bindingResult.hasErrors()) {
            return "users/registerUser";
        }
        userService.registerUserEntity(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "users/registerUser";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }


    @GetMapping("")
    public String findAllUsersPage(Model model, @PageableDefault(size = 5)Pageable pageable) {
        model.addAttribute("users", userService.findAllUsersPage(pageable));
        return "users/users";
    }

    /*@GetMapping("")
    public String findAllUsersList(Model model) {
        model.addAttribute("users", userService.findAllUsersList());
        return "users/users";
    }*/



    //DTO
    /*@GetMapping("/register")
    public String registerUserGetDto(Model model) throws IOException {
        model.addAttribute("user",new UserRegisterDTO());;
        return "users/registerUser";
    }

    @PostMapping("/register")
    public String registerUserPostDto(@ModelAttribute UserRegisterDTO dto) throws IOException {
        userService.registerUserDTO(dto);
        return "redirect:/users";
    }
    */


//    @GetMapping("/edit/{id}")
//    public String editUserGetDto(Model model,@PathVariable("id") long id) throws IOException {
//        model.addAttribute("dto",userService.findUserById(id));
//        return "users/registerUser";
//    }


    //Rest
    @PostMapping("/rest")
    public @ResponseBody
    UserShowDTO registerUserRest(@ModelAttribute UserRegisterDTO user) throws IOException {
        return userService.registerUserDTO(user);
    }

    @DeleteMapping("/rest/{id}")
    public @ResponseBody
    void deleteUserRest(@PathVariable long id) {
        userService.delete(id);
    }


    //Rest
    //DTO
    /*@PostMapping("/rest/dto")
    public @ResponseBody
    UserShowDTO registerUserDTO(@RequestBody UserDTO dto) {
        return userService.registerUserWithoutCover(dto);
    }*/

    @GetMapping("/rest/dto")
    public @ResponseBody
    List<UserShowDTO> findAllUsersDTORest() {
        return userService.findAllUsersDTO();
    }

    @GetMapping("/rest/dto/{id}")
    public @ResponseBody
    UserShowDTO findUserDTORest(@PathVariable long id) {
        return userService.findUserByIdDTO(id);
    }

    //REST
    //Entity
    @GetMapping("/rest")
    public @ResponseBody
    List<User> findAllUsersRest() {
        return userService.findAllUsersList();
    }

    @GetMapping("/rest/{id}")
    public @ResponseBody
    User findUserRest(@PathVariable long id) {
        return userService.findUserById(id);
    }
}
