package javer.drinkappspringversion.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties({"strDrinkAlternate", "strDrinkES", "strDrinkDE", "strDrinkFR",
        "strDrinkZH-HANS", "strDrinkZH-HANT", "strTags", "strVideo", "strIBA",
        "strInstructionsES", "strInstructionsDE", "strInstructionsFR", "strInstructionsZH-HANS",
        "strInstructionsZH-HANT", "strDrinkThumb", "strCreativeCommonsConfirmed"})
@JsonDeserialize(using = DrinkDeserializer.class)
@Data
@ToString
@Getter@Setter
public class DrinkAPI {

    @JsonProperty("idDrink")
    private Long id;

    @JsonProperty("strDrink")
    private String name;

    @JsonProperty("strInstructions")
    private String recipe;

    @JsonProperty("strCategory")
    private String category;

    @JsonProperty("strAlcoholic")
    private String drinkType;

    @JsonProperty("strGlass")
    private String glassType;

    @JsonProperty("dateModified")
    private String modificationDate;

    @JsonProperty("strDrinkThumb")
    private String imageUrl;

    private Map<String, String> ingredients = new HashMap<>();
}
