package javer.drinkappspringversion.service;

import javer.drinkappspringversion.dto.MessageDto;
import javer.drinkappspringversion.model.Message;
import javer.drinkappspringversion.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Optional<Message> get(Long id) {
        return messageRepository.getById(id);
    }

    @Override
    public void leaveMessage(Long id, String information) {
        MessageDto messageDto = MessageDto.builder()
                .id(id)
                .information(information)
                .build();
        if (messageRepository.getById(id).isEmpty()) {
            save(messageDto);
        } else {
            update(id, information);
        }
    }

    @Override
    public Message save(MessageDto messageDto) {
        return messageRepository.save(Message
                .builder()
                .id(messageDto.getId())
                .information(messageDto.getInformation())
                .build());
    }

    @Override
    public void update(Long id, String information) {
        Optional<Message> message = messageRepository.getById(id);
        if (message.isPresent()) {
            Message messageToUpdate = message.get();
            messageToUpdate.setId(id);
            messageToUpdate.setInformation(information);
            messageRepository.save(messageToUpdate);
        }
    }
}
