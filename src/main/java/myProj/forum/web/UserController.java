package myProj.forum.web;

import myProj.forum.domain.User;
import myProj.forum.service.UserService;
import org.apache.ibatis.annotations.*;
import java.util.List;
import myProj.forum.domain.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute User user) {
        user.setRole("USER");
        userService.save(user);
        return "redirect:/login";
    }
}