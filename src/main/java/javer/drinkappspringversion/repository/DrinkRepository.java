package javer.drinkappspringversion.repository;

import javer.drinkappspringversion.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink,Long> {

    Drink findByName(String name);

}