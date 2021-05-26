package javer.drinkappspringversion.service.web.controller;

import javer.drinkappspringversion.service.DrinkService;
import javer.drinkappspringversion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final DrinkService drinkService;
    private final UserService userService;

    @GetMapping("/user-view")
    public String showUserView(@RequestParam Integer page, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("drinks", drinkService.getAllDrinks());
        model.addAttribute("pageNumbers", drinkService.countsPages(8));
        model.addAttribute("requestDrinks", drinkService.getRequestDrinkList(page, 8));
        model.addAttribute("user", userService.get(authentication.getName()));
        return "user-view";
    }
}
