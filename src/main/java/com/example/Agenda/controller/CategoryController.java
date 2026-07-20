package com.example.Agenda.controller;

import com.example.Agenda.Model.CategoryEntity;
import com.example.Agenda.controller.dto.CategoryDTO;
import com.example.Agenda.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;
import java.util.List;

@Controller
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryEntity categoryEntity = categoryDTO.mappingToCategory();
        categoryService.saveCategory(categoryEntity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoryEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") String id){
        var idCategory = UUID.fromString(id);
        CategoryEntity category = categoryService.findById(idCategory);

        CategoryDTO categoryDTO = new CategoryDTO(category.getTitle());

        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping
    public  ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categoryEntityList = categoryService.findAll()
                .stream()
                .map(CategoryDTO::fromEntity)
                .toList();

        if (categoryEntityList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categoryEntityList);
    }
}
