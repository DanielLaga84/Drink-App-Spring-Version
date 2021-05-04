package javer.drinkappspringversion.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class DrinkDeserializer extends JsonDeserializer<DrinkAPI> {
    @Override
    public DrinkAPI deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Map<String,String> ingredients = new HashMap<>();
        DrinkAPI drinkAPI = new DrinkAPI();
        JsonNode tree = jsonParser.readValueAsTree();
        String[] errors = {"null"};
        for (int index = 1; index < 16; index++ ) {
            JsonNode ingredientNode = tree.get("strIngredient" + index);
            if ( ingredientNode == null ) {
                break;
            }
            String trim = ingredientNode.asText().trim();
            for ( String error : errors) {
                if ( !trim.equals(error) && !trim.isEmpty()) {
                    ingredients.put(tree.get("strIngredient" + index).asText().trim(),tree.get("strMeasure" + index).asText().trim());
                }
            }
        }
        drinkAPI.setId(tree.get("idDrink").asLong());
        drinkAPI.setName(tree.get("strDrink").asText());
        drinkAPI.setRecipe(tree.get("strInstructions").asText());
        drinkAPI.setDrinkType(tree.get("strAlcoholic").asText());
        drinkAPI.setCategory(tree.get("strCategory").asText());
        drinkAPI.setGlassType(tree.get("strGlass").asText());
        if((tree.get("dateModified")).isNull()) {
            String datePattern = getNewDatePattern();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            drinkAPI.setModificationDate(LocalDateTime.now().format(formatter));
        }else {
            drinkAPI.setModificationDate(tree.get("dateModified").asText());
        }
        drinkAPI.setImageUrl(tree.get("strDrinkThumb").asText());
        drinkAPI.setIngredients(ingredients);
        return drinkAPI;
    }

    private String getNewDatePattern() {
        Properties settings = new Properties();
        try (InputStream inputStream = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("settings.properties")).openStream()) {
            settings.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return settings.getProperty("date.format");
    }
}
