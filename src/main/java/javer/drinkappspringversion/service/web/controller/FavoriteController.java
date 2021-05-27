package javer.drinkappspringversion.service.web.controller;

import javer.drinkappspringversion.service.DrinkService;
import javer.drinkappspringversion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FavoriteController {

    private final DrinkService drinkService;
    private final UserService userService;

    @GetMapping("/user-favorite-view")
    public String showUserView(@RequestParam Integer page, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("drinks", drinkService.getAllDrinks());
        model.addAttribute("drinksFav", userService.get(authentication.getName()).getFavouriteDrinkList());
        model.addAttribute("pageNumbers", drinkService.countsPages(8));
        model.addAttribute("requestDrinks", drinkService.getRequestDrinkList(page, 8));
        model.addAttribute("favPageNumbers", userService.favCountsPages(8,authentication.getName()));
        model.addAttribute("favRequestDrinks", userService.getRequestFavDrinkList(page, 8, authentication.getName()));
        model.addAttribute("user", userService.get(authentication.getName()));
        return "user-favorite-view";
    }
}

