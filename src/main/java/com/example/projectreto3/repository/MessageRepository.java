package com.example.projectreto3.repository;

import com.example.projectreto3.entities.Message;
import com.example.projectreto3.repository.crud.MessageCRUDrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepository {

    @Autowired
    private MessageCRUDrepository messageCRUDrepository;

    public List<Message> getAll() {

        return (List<Message>) messageCRUDrepository.findAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageCRUDrepository.findById(id);
    }

    public Message save(Message message) {
        return messageCRUDrepository.save(message);
    }

    public void delete(Message message){
        messageCRUDrepository.delete(message);
    }
}
