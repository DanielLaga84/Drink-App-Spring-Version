package javer.drinkappspringversion.repository;

import javer.drinkappspringversion.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    Optional<Message> getById(Long id);
}
