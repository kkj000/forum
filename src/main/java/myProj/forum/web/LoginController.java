package myProj.forum.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout1(){
        return"redirect:/";
    }
    @PostMapping("/logout")
    public String logout(){
        return"redirect:/";
    }



}