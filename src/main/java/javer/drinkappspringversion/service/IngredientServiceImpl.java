package javer.drinkappspringversion.service;

import javer.drinkappspringversion.model.Ingredient;
import javer.drinkappspringversion.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Set<String> getUniqueIngredientNames() {
        return ingredientRepository.findAll().stream().map(Ingredient::getName).collect(Collectors.toSet());
    }
}
