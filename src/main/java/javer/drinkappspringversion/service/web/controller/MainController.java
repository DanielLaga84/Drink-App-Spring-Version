package javer.drinkappspringversion.service.web.controller;

import javer.drinkappspringversion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String start() {
        userService.createAdmin();
        return "age-query";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/adult-check")
    public String adultCheck(@RequestParam("age") String age) {
        if (age.equals("18+")) {
            return "redirect:/home";
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
        if (navigator.equals("ADMIN")) {
            return "redirect:admin-panel";
        } else
            return "redirect:user-view";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}