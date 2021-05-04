package javer.drinkappspringversion.parser.mapper;

import javer.drinkappspringversion.model.Drink;
import javer.drinkappspringversion.parser.DrinkAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrinkMapper {

    private final IngredientMapper ingredientMapper;

    public Drink mapDrink(DrinkAPI drinkAPI) {
        return Drink.builder()
                .id(drinkAPI.getId())
                .name(drinkAPI.getName())
                .drinkType(drinkAPI.getDrinkType())
                .glassType(drinkAPI.getGlassType())
                .recipe(drinkAPI.getRecipe())
                .ingredientList(ingredientMapper.mapIngredients(drinkAPI))
                .modificationDate(drinkAPI.getModificationDate())
                .imageUrl(drinkAPI.getImageUrl())
                .category(drinkAPI.getCategory())
                .isCustom(false)
                .isApproved(true)
                .build();
    }
}