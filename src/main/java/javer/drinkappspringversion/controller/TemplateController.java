package javer.drinkappspringversion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TemplateController {
    @GetMapping("/")
    public String start() {
        return "age-query";
    }

    @GetMapping("/adult_check")
    public String adultCheck(@RequestParam("age") String age) {
        if (age.equals("18+")) {
            return "GOOD";
        } else {
            return "redirect:goodbye";
        }
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "goodbye";
    }
}

