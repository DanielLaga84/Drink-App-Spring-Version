package javer.drinkappspringversion.service;

import javer.drinkappspringversion.dto.DrinkDto;
import javer.drinkappspringversion.dto.IngredientDto;
import javer.drinkappspringversion.model.Drink;
import javer.drinkappspringversion.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;
    private final MessageService messageService;

    @Override
    public void save(DrinkDto drinkDto) {
        String drinkDtoName = drinkDto.getName();
        if (drinkRepository.findByName(drinkDtoName) != null) {
            messageService.leaveMessage(1L, "Drink called `" + drinkDtoName + "` already exists!");
        } else {
            messageService.leaveMessage(1L, "Drink called `" + drinkDtoName + "` saved!");
            drinkRepository.save(Drink.builder()
                    .name(drinkDto.getName())
                    .isCustom(true)
                    .isApproved(true)
                    .recipe(drinkDto.getRecipe())
                    .drinkType(drinkDto.getDrinkType())
                    .glassType(drinkDto.getGlassType())
                    .modificationDate(String.valueOf(LocalDate.now()))
                    .imageUrl(drinkDto.getImageUrl())
                    .category(drinkDto.getCategory())
                    .ingredientList(drinkDto.getIngredientList().stream().map(IngredientDto::dtoToIngredient).collect(Collectors.toList()))
                    .build());
        }
    }

    @Override
    public void delete(String name) {
        if (drinkRepository.findByName(name) == null) {
            messageService.leaveMessage(1L, "Drink called '" + name + "' does not exist!");
        } else {
            messageService.leaveMessage(1L, "Drink called '" + name + "' has been deleted!");
            drinkRepository.delete(drinkRepository.findByName(name));
        }
    }

    @Override
    public Drink get(String name) {
        return drinkRepository.findByName(name);
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    @Override
    public Set<String> getUniqueGlass() {
        return drinkRepository.findAll().stream().map(Drink::getGlassType).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getUniqueCategory() {
        return drinkRepository.findAll().stream().map(Drink::getCategory).collect(Collectors.toSet());
    }

    @Override
    public List<Integer> countsPages(Integer numberOfDrinks) {
        int sizeOfDrink = getAllDrinks().size();
        List<Integer> pages = new ArrayList<>();
        numberOfDrinks = sizeOfDrink % numberOfDrinks == 0 ? sizeOfDrink / numberOfDrinks : sizeOfDrink / numberOfDrinks + 1;
        for (int pageNumber = 1; pageNumber <= numberOfDrinks; pageNumber++) {
            pages.add(pageNumber);
        }
        return pages;
    }

    @Override
    public List<Drink> getRequestDrinkList(Integer pageNumber, Integer numberOfDrinks) {
        int fromIndex = (pageNumber - 1) * numberOfDrinks;
        int toIndex = pageNumber * numberOfDrinks;
        if (toIndex > getAllDrinks().size()) {
            toIndex = getAllDrinks().size();
        }
        return getAllDrinks().subList(fromIndex, toIndex);
    }
}
