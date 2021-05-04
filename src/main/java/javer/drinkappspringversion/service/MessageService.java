package javer.drinkappspringversion.service;

import javer.drinkappspringversion.dto.MessageDto;
import javer.drinkappspringversion.model.Message;

import java.util.Optional;

public interface MessageService {

    Optional<Message> get(Long id);

    void leaveMessage(Long id, String information);

    Message save(MessageDto messageDto);

    void update(Long id, String information);
}
