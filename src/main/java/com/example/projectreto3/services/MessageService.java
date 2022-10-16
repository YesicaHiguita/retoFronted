package com.example.projectreto3.services;

import com.example.projectreto3.entities.Client;
import com.example.projectreto3.entities.Message;
import com.example.projectreto3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int messageId) {
        return messageRepository.getMessage(messageId);
    }

    public Message save(Message c){
        if(c.getIdMessage()==null){
            return messageRepository.save(c);
        }else{
            Optional<Message> pa=messageRepository.getMessage(c.getIdMessage());
            if(!pa.isPresent()){
                return messageRepository.save(c);
            }
        }
        return c;

    }

    public Message update(Message a){
        if(a.getIdMessage()!=null){
            Optional<Message> pa=messageRepository.getMessage(a.getIdMessage());
            if(pa.isPresent()){
                Message result=pa.get();

                if(a.getMessageText()!=null){
                    result.setMessageText(a.getMessageText());
                }
                if(a.getAudience()!=null){
                    result.setAudience(a.getAudience());
                }
                if(a.getClient()!=null){
                    result.setClient(a.getClient());
                }

                return messageRepository.save(result);
            }
        }
        return a;
    }

    public boolean deleteMessage (int id){
        Boolean d = getMessage(id).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return d;
    }
}
