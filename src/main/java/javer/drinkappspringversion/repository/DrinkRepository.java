package javer.drinkappspringversion.repository;

import javer.drinkappspringversion.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink,Long> {

    Drink findByName(String name);
}
