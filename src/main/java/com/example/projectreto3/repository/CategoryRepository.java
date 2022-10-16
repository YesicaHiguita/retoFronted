package com.example.projectreto3.repository;

import com.example.projectreto3.entities.Category;
import com.example.projectreto3.repository.crud.CategoryCRUDrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {

    @Autowired
    public CategoryCRUDrepository categoryCRUDrepository;

    public List<Category> getAll(){
        return (List<Category>) categoryCRUDrepository.findAll();
    }

    public Category save(Category c){
        return categoryCRUDrepository.save(c);
    }

    public Optional<Category> getCategory(int id){
        return categoryCRUDrepository.findById(id);
    }

    public void delete(Category category){
        categoryCRUDrepository.delete(category);
    }
}
