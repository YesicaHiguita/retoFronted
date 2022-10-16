package com.example.projectreto3.repository;

import com.example.projectreto3.entities.Admin;
import com.example.projectreto3.entities.Audience;
import com.example.projectreto3.repository.crud.AdminCRUDrepository;
import com.example.projectreto3.repository.crud.AudienceCRUDrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {
    @Autowired
    private AdminCRUDrepository adminCRUDrepository;

    public List<Admin> getAll(){
        return (List<Admin>) adminCRUDrepository.findAll();
    }

    public Admin save(Admin c){
        return adminCRUDrepository.save(c);
    }

    public Optional<Admin> getById(int i){
        return  adminCRUDrepository.findById(i);
    }

    public void delete(Admin admin){adminCRUDrepository.delete(admin);
    }
}
