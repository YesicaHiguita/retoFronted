package com.example.projectreto3.services;

import com.example.projectreto3.entities.Audience;
import com.example.projectreto3.repository.AudienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudienceService {

    @Autowired
    private AudienceRepository audienceRepository;

    public List<Audience> getAll(){
        return audienceRepository.getAll();
    }

    public Audience save(Audience c){
        if(c.getId()==null){
            return audienceRepository.save(c);
        }else{
            Optional<Audience> pa=audienceRepository.getById(c.getId());
            if(!pa.isPresent()){
                return audienceRepository.save(c);
            }
        }
        return c;

    }

    public Optional<Audience> getById(int id){
        return audienceRepository.getById(id);
    }

    public Audience update(Audience a){
        System.out.println("entropor uno");
        if(a.getId()!=null){
            System.out.println("entro por dos");
            Optional<Audience> pa=audienceRepository.getById(a.getId());
            if(pa.isPresent()){
                Audience result=pa.get();
                System.out.println("entro");
                if(a.getCapacity()!=null){
                    result.setCapacity(a.getCapacity());
                }
                if(a.getCategory()!=null){
                    result.setCategory(a.getCategory());
                }
                if(a.getDescription()!=null){
                    result.setDescription(a.getDescription());
                }
                if(a.getName()!=null){
                    result.setName(a.getName());
                }
                if(a.getOwner()!=null){
                    result.setOwner(a.getOwner());
                }
                return audienceRepository.save(result);
            }
        }
        return a;
    }

    public boolean deleteAudience (int id){
        Boolean d = getById(id).map(audience -> {
            audienceRepository.delete(audience);
            return true;
        }).orElse(false);
        return d;
    }
}
