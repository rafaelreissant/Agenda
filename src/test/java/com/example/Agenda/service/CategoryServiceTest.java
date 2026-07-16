package com.example.Agenda.service;

import com.example.Agenda.Model.CategoryEntity;
import com.example.Agenda.Repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void createCategory(){
        CategoryEntity categorySave = new CategoryEntity(
                UUID.fromString("14ed8a26-c601-40a1-b4e2-984ec2ca5400"),
                "Futebol");

        categoryService.saveCategory(categorySave);

        verify(categoryRepository).save(categorySave);
    }

    @Test
    void getCategoryById(){
        CategoryEntity categoryMock = new CategoryEntity(
                UUID.fromString("14ed8a26-c601-40a1-b4e2-984ec2ca5400"),
                "Futebol");

        when(categoryRepository.findById(UUID.fromString("14ed8a26-c601-40a1-b4e2-984ec2ca5400")))
                .thenReturn(Optional.of(categoryMock));

        CategoryEntity categoryActual = categoryService.findById(
                UUID.fromString("14ed8a26-c601-40a1-b4e2-984ec2ca5400"));

        assertNotNull(categoryActual);
        assertEquals("Futebol", categoryActual.getTitle());
    }

    @Test
    void getAllCategory(){
        CategoryEntity category1 = new CategoryEntity(
                UUID.fromString("12ca1ca9-8f92-4b87-8af8-da60657c50d1"), "Alice");
        CategoryEntity category2 = new CategoryEntity(
                UUID.fromString("799b99ba-967d-4795-9763-fd2d79c77c58"), "Bob");
        List<CategoryEntity> categoryEntityList = Arrays.asList(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categoryEntityList);

        List<CategoryEntity> categoryList = categoryService.findAll();

        assertNotNull(categoryList);
        assertEquals(2, categoryList.size(), "The user list size should be 2");
        assertEquals("Alice", categoryList.get(0).getTitle());
        assertEquals("Bob", categoryList.get(1).getTitle());
    }

    @Test
    void getUserNullById(){}

}