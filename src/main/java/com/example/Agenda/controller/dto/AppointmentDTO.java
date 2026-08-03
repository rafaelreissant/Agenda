package com.example.Agenda.controller.dto;

import com.example.Agenda.Enums.Priority;
import com.example.Agenda.Enums.Status;
import com.example.Agenda.Model.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AppointmentDTO(
        @NotBlank(message = "Title is required")
        String title,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime startDateTime,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime endDateTime,
        Priority priority,
        Status status,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        CategoryEntity categoryEntity) {
}