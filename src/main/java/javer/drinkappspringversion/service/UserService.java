package javer.drinkappspringversion.service;

import javer.drinkappspringversion.dto.UserRegistrationDto;
import javer.drinkappspringversion.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto userRegistrationDto);

    User get(String email);

}
