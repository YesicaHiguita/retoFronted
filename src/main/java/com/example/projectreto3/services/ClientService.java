package com.example.projectreto3.services;

import com.example.projectreto3.entities.Category;
import com.example.projectreto3.entities.Client;
import com.example.projectreto3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int clientId) {
        return clientRepository.getClient(clientId);
    }

    public Client save(Client c){
        if(c.getIdClient()==null){
            return clientRepository.save(c);
        }else{
            Optional<Client> pa=clientRepository.getClient(c.getIdClient());
            if(!pa.isPresent()){
                return clientRepository.save(c);
            }
        }
        return c;

    }

    public Client update(Client a){
        if(a.getIdClient()!=null){
            Optional<Client> pa=clientRepository.getClient(a.getIdClient());
            if(pa.isPresent()){
                Client result=pa.get();

                if(a.getEmail()!=null){
                    result.setEmail(a.getEmail());
                }
                if(a.getPassword()!=null){
                    result.setPassword(a.getPassword());
                }
                if(a.getName()!=null){
                    result.setName(a.getName());
                }
                if(a.getAge()!=null){
                    result.setAge(a.getAge());
                }

                return clientRepository.save(result);
            }
        }
        return a;
    }

    public boolean deleteClient (int id){
        Boolean d = getClient(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return d;
    }
}
