package com.example.Agenda.Repository;

import com.example.Agenda.Model.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testSaveCategory(){
        CategoryEntity category = new CategoryEntity();
        category.setTitle("Test");

        CategoryEntity savedCategory = categoryRepository.save(category);

        assertNotNull(savedCategory.getId());
    }
}