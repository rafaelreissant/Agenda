package com.example.Agenda.controller.dto;

import com.example.Agenda.Model.CategoryEntity;

public record CategoryDTO(String title) {

    public CategoryEntity mappingToCategory(){
        CategoryEntity category = new CategoryEntity();
        category.setTitle(this.title);
        return category;
    }

    public static CategoryDTO fromEntity(CategoryEntity category) {
        return new CategoryDTO(category.getTitle());
    }
}
