package javer.drinkappspringversion.config.repository;

import javer.drinkappspringversion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
