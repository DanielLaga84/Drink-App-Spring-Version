package javer.drinkappspringversion.dto;

import javer.drinkappspringversion.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {

    private Long id;
    private String name;
    private String measure;

    public static IngredientDto ingredientToDto(Ingredient ingredient) {
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredient.getId());
        ingredientDto.setName(ingredient.getName());
        ingredientDto.setMeasure(ingredient.getMeasure());
        return ingredientDto;
    }

    public static Ingredient dtoToIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDto.getName());
        ingredient.setMeasure(ingredientDto.getMeasure());
        return ingredient;
    }
}
