package com.example.projectreto3.repository;

import com.example.projectreto3.entities.Client;
import com.example.projectreto3.repository.crud.ClientCRUDrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    @Autowired
    private ClientCRUDrepository clientCRUDrepository;

    public List<Client> getAll(){
        return (List<Client>) clientCRUDrepository.findAll();
    }

    public Optional<Client> getClient(int id){
        return clientCRUDrepository.findById(id);
    }

    public Client save(Client client){
        return clientCRUDrepository.save(client);
    }

    public void delete(Client client){
        clientCRUDrepository.delete(client);
    }
}
