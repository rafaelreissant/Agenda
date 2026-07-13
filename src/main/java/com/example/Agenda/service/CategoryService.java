package com.example.Agenda.service;

import com.example.Agenda.Model.CategoryEntity;
import com.example.Agenda.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void saveCategory(CategoryEntity categoryEntity){
        categoryRepository.save(categoryEntity);
    }

    public List<CategoryEntity> findAll(){
        return categoryRepository.findAll();
    }

    public CategoryEntity findById(UUID id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}