package com.example.projectreto3.services;

import com.example.projectreto3.entities.Audience;
import com.example.projectreto3.entities.Category;
import com.example.projectreto3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    public Category save(Category c){
        if(c.getId()==null){
            return categoryRepository.save(c);
        }else{
            Optional<Category> pa=categoryRepository.getCategory(c.getId());
            if(!pa.isPresent()){
                return categoryRepository.save(c);
            }
        }
        return c;

    }

    public Category update(Category a){
        if(a.getId()!=null){
            Optional<Category> pa=categoryRepository.getCategory(a.getId());
            if(pa.isPresent()){
                Category result=pa.get();

                if(a.getDescription()!=null){
                    result.setDescription(a.getDescription());
                }
                if(a.getName()!=null){
                    result.setName(a.getName());
                }

                return categoryRepository.save(result);
            }
        }
        return a;
    }

    public boolean deleteCategory (int id){
        Boolean d = getCategory(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
}
