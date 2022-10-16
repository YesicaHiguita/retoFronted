package com.example.projectreto3.repository;

import com.example.projectreto3.entities.Audience;
import com.example.projectreto3.repository.crud.AudienceCRUDrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AudienceRepository {

    @Autowired
    private AudienceCRUDrepository audienceCRUDrepository;

    public List<Audience> getAll(){
        return (List<Audience>) audienceCRUDrepository.findAll();
    }

    public Audience save(Audience c){
        return audienceCRUDrepository.save(c);
    }

    public Optional<Audience> getById(int i){
        return  audienceCRUDrepository.findById(i);
    }

   public void delete(Audience audience){audienceCRUDrepository.delete(audience);
    }


}
