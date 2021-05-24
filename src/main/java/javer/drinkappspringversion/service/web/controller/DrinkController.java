package javer.drinkappspringversion.service.web.controller;

import javer.drinkappspringversion.service.DrinkService;
import javer.drinkappspringversion.service.MessageService;
import javer.drinkappspringversion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService drinkService;
    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/drink")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String drinkView(@RequestParam(name = "name") String name, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().isEmpty()) {
            return "redirect:login:logout";
        }
        model.addAttribute("drink", drinkService.get(name));
        model.addAttribute("drinks", drinkService.getAllDrinks());
        model.addAttribute("message", messageService.get(3L));
        model.addAttribute("favourite", userService.isFavourite(name, authentication.getName()));
        return "drink-view";
    }

    @PostMapping("favourite-drink")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String favouriteDrink(@RequestParam(name = "name") String name, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.manageFavourite(name, authentication.getName());
        model.addAttribute("drink", drinkService.get(name));
        model.addAttribute("drinks", drinkService.getAllDrinks());
        model.addAttribute("message", messageService.get(2L));
        model.addAttribute("favourite", userService.isFavourite(name, authentication.getName()));
        return "drink-view";
    }
}