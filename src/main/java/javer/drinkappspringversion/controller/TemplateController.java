package javer.drinkappspringversion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TemplateController {

    private final UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return UserRegistrationDto.builder().build();
    }

    @GetMapping("/")
    public String start() {
        return "age-query";
    }
    @GetMapping("/adult-check")
    public String adultCheck(@RequestParam("age") String age) {
        if (age.equals("18+")) {
            return "index";
        } else {
            return "redirect:goodbye";
        }
    }
    @GetMapping("/goodbye")
    public String goodbye() {
        return "goodbye";
    }
    @GetMapping("/navigation")
    public String navigation(@RequestParam("navigator") String navigator) {
        if (navigator.equals("Login")) {
            return "redirect:login";
        } else
            return "redirect:register";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserRegistrationDto user) {
        userService.saveUser(user);
        return "redirect:register";
    }
}