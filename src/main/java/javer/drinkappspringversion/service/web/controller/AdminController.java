package javer.drinkappspringversion.service.web.controller;

import javer.drinkappspringversion.service.DrinkService;
import javer.drinkappspringversion.service.IngredientService;
import javer.drinkappspringversion.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    public static final String ADMIN_PANEL = "admin-panel";
    public static final String MESSAGE = "message";
    private final DrinkService drinkService;
    private final MessageService messageService;
    private final IngredientService ingredientService;

    @GetMapping("/admin-panel")
    public String showAdminPanel(Model model) {
        addAttributes(model);
        model.addAttribute(MESSAGE, messageService.get(3L));
        return ADMIN_PANEL;
    }

    private void addAttributes(Model model) {
        model.addAttribute("drinks", drinkService.getAllDrinks());
        model.addAttribute("categories", drinkService.getUniqueCategory());
        model.addAttribute("glasses", drinkService.getUniqueGlass());
        model.addAttribute("ingredients", ingredientService.getUniqueIngredientNames());
    }
}

