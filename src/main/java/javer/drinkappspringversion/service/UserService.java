package javer.drinkappspringversion.service;

import javer.drinkappspringversion.dto.UserRegistrationDto;
import javer.drinkappspringversion.model.Drink;
import javer.drinkappspringversion.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto userRegistrationDto);

    User get(String email);

    void createAdmin();

    void createUser();

    void manageFavourite(String drinkName, String userEmail);

    void addFavourite(String drinkName, String userEmail);

    void deleteFavourite(String drinkName, String userEmail);

    Optional<Drink> isFavourite(String drinkName, String userEmail);

    List<String> favouriteDrinkList(String userEmail);

}
