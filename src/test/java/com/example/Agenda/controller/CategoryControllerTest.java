package com.example.Agenda.controller;

import com.example.Agenda.Model.CategoryEntity;
import com.example.Agenda.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    @Test
    void createCategory() throws Exception {
        doNothing().when(categoryService).saveCategory(any(CategoryEntity.class));

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "title": "Dentista"
                }
                """))
                .andExpect(status().isCreated());

        verify(categoryService).saveCategory(any(CategoryEntity.class));

    }

    @Test
    void getCategory() throws Exception {
        UUID idCategory = UUID.fromString("ee566cfd-cae2-4cf9-8a73-e7d358360822");
        CategoryEntity categoryEntity = new CategoryEntity(idCategory, "Sucesso");

        when(categoryService.findById(idCategory)).thenReturn(categoryEntity);

        // When & Then
        mockMvc.perform(get("/categories/{id}", idCategory)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sucesso"));
    }

    @Test
    void getAllCategories() throws Exception {
        UUID idCategory1 = UUID.fromString("cc965c38-cde2-40a3-9d71-4cd080653243");
        CategoryEntity categoryEntity1 = new CategoryEntity(idCategory1, "Sucesso");

        UUID idCategory2 = UUID.fromString("3fc9525d-13ed-4fb4-be71-6752d1e7f04d");
        CategoryEntity categoryEntity2 = new CategoryEntity(idCategory2, "Maravilha");

        List<CategoryEntity> categoryEntityList = Arrays.asList(categoryEntity1, categoryEntity2);

        when(categoryService.findAll()).thenReturn(categoryEntityList);

        mockMvc.perform(get("/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Sucesso"))
                .andExpect(jsonPath("$[1].title").value("Maravilha"));

        verify(categoryService).findAll();

    }
}