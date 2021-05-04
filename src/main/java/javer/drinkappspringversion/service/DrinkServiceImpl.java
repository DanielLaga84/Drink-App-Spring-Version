package javer.drinkappspringversion.service;

import javer.drinkappspringversion.dto.DrinkDto;
import javer.drinkappspringversion.dto.IngredientDto;
import javer.drinkappspringversion.model.Drink;
import javer.drinkappspringversion.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if(drinkRepository.findByName(drinkDtoName) != null) {
            messageService.leaveMessage(1L,"Drink called `" + drinkDtoName + "` already exists!");
        } else {
            messageService.leaveMessage(1L,"Drink called `" + drinkDtoName + "` saved!");
            drinkRepository.save(Drink.builder()
                    .name(drinkDto.getName())
                    .isCustom(true)
                    .isApproved(true)
                    .recipe(drinkDto.getRecipe())
                    .drinkType(drinkDto.getRecipe())
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

    }

    @Override
    public Drink get(String name) {
        return null;
    }

    @Override
    public List<Drink> getAllDrinks() {
        return null;
    }

    @Override
    public Set<String> getUniqueGlass() {
        return null;
    }

    @Override
    public Set<String> getUniqueCategory() {
        return null;
    }

    @Override
    public List<Integer> countsPages(Integer numberOfDrinks) {
        return null;
    }

    @Override
    public List<Drink> getRequestDrinkList(Integer pageNumber, Integer numberOfDrinks) {
        return null;
    }
}
