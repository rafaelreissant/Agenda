package com.example.Agenda.Model;

import com.example.Agenda.Enums.Priority;
import com.example.Agenda.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String title;

    @Size(max = 500)
    private String description;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;
}