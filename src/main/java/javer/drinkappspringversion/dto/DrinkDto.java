package javer.drinkappspringversion.dto;

import javer.drinkappspringversion.model.Drink;
import javer.drinkappspringversion.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrinkDto {

    private Long id;
    private String name;
    private Boolean isCustom;
    private Boolean isApproved;
    private String recipe;
    private String drinkType;
    private String glassType;
    private String modificationDate;
    private String imageUrl;
    private String category;
    private List<IngredientDto> ingredientList;

    public static DrinkDto drinkToDto(Drink drink) {
        DrinkDto drinkDto = new DrinkDto();
        drinkDto.setId(drink.getId());
        drinkDto.setName(drink.getName());
        drinkDto.setIsCustom(drink.getIsCustom());
        drinkDto.setIsApproved(drink.getIsApproved());
        drinkDto.setRecipe(drink.getRecipe());
        drinkDto.setDrinkType(drink.getDrinkType());
        drinkDto.setGlassType(drink.getGlassType());
        drinkDto.setModificationDate(drink.getModificationDate());
        drinkDto.setImageUrl(drink.getImageUrl());
        drinkDto.setCategory(drink.getCategory());

        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        drink.getIngredientList().forEach(ingredient -> {
            IngredientDto ingredientDto = IngredientDto.ingredientToDto(ingredient);
            ingredientDtoList.add(ingredientDto);
        });
        drinkDto.setIngredientList(ingredientDtoList);
        return drinkDto;
    }

    public static Drink DtoToDrink(DrinkDto drinkDto) {
        Drink drink = new Drink();
        drink.setName(drinkDto.getName());
        drink.setIsCustom(drinkDto.getIsCustom());
        drink.setIsApproved(drinkDto.getIsApproved());
        drink.setRecipe(drinkDto.getRecipe());
        drink.setDrinkType(drinkDto.getDrinkType());
        drink.setGlassType(drinkDto.getGlassType());
        drink.setModificationDate(drinkDto.getModificationDate());
        drink.setImageUrl(drinkDto.getImageUrl());
        drink.setCategory(drinkDto.getCategory());

        List<Ingredient> ingredientList = new ArrayList<>();
        drinkDto.getIngredientList().forEach(ingredientDto -> {
            Ingredient ingredient = IngredientDto.dtoToIngredient(ingredientDto);
            ingredientList.add(ingredient);
        });
        drink.setIngredientList(ingredientList);
        return drink;

    }

}