package javer.drinkappspringversion.parser.service;

import javer.drinkappspringversion.dto.DrinkDto;
import javer.drinkappspringversion.model.Drink;
import javer.drinkappspringversion.parser.DrinkAPI;
import javer.drinkappspringversion.parser.ParserService;
import javer.drinkappspringversion.parser.mapper.DrinkMapper;
import javer.drinkappspringversion.service.DrinkService;
import javer.drinkappspringversion.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FileParserService {

    private final ParserService parserService;
    private final DrinkService drinkService;
    private final MessageService messageService;
    private final DrinkMapper drinkMapper;

    public void parseDataToDatabase(File json) {
        List<DrinkAPI> drinkAPIList = parserService.parseFile(json);
        if (drinkAPIList != null) {
            int size = drinkAPIList.size();
            int count = 0;
            for (DrinkAPI drinkAPI : drinkAPIList) {
                if (drinkService.getAllDrinks().stream().noneMatch(drink -> drink.getName().equals(drinkAPI.getName()))) {
                    count++;
                    Drink drink = Optional
                            .ofNullable(drinkService.get(drinkAPI.getCategory()))
                            .orElseGet(() -> drinkMapper.mapDrink(drinkAPI));

                    drinkService.getAllDrinks().add(drinkMapper.mapDrink(drinkAPI));
                    drinkService.save(DrinkDto.drinkToDto(drink));
                }
            }
            messageService.leaveMessage(1L, count + " items was parsed from " + size);
        } else {
            messageService.leaveMessage(1L, "The file wasn't loaded");
        }
    }
}