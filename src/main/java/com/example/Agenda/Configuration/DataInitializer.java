package com.example.Agenda.Configuration;

import com.example.Agenda.Enums.Priority;
import com.example.Agenda.Enums.Status;
import com.example.Agenda.Model.AppointmentEntity;
import com.example.Agenda.Model.CategoryEntity;
import com.example.Agenda.Repository.AppointmentRepository;
import com.example.Agenda.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@Transactional
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AppointmentRepository appointmentRepository,
                                   CategoryRepository categoryRepository){
        return args -> {
            if (appointmentRepository.count() == 0){
                CategoryEntity category1 = new CategoryEntity();
                category1.setTitle("Viagem");
                categoryRepository.save(category1);

                AppointmentEntity appointment1 = new AppointmentEntity();
                appointment1.setTitle("Test");
                appointment1.setDescription("Testando o funcionamento");
                appointment1.setStartDateTime(LocalDateTime.of(2026, 7, 5, 14, 30));
                appointment1.setEndDateTime(LocalDateTime.of(2026, 7, 5, 15, 30));
                appointment1.setPriority(Priority.MEDIUM);
                appointment1.setStatus(Status.SCHEDULED);
                appointment1.setCategoryEntity(category1);

                appointmentRepository.save(appointment1);
            }
        };
    }
}