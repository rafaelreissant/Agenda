package com.example.Agenda.Repository;

import com.example.Agenda.Enums.Priority;
import com.example.Agenda.Enums.Status;
import com.example.Agenda.Model.AppointmentEntity;
import com.example.Agenda.Model.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void setAppointmentRepository() {
        CategoryEntity category = new CategoryEntity();
        category.setTitle("Test");

        AppointmentEntity appointment = new AppointmentEntity();

        appointment.setTitle("Consulta");
        appointment.setDescription("Consulta de rotina");
        appointment.setStartDateTime(LocalDateTime.of(2026, 7, 20, 14, 0));
        appointment.setEndDateTime(LocalDateTime.of(2026, 7, 20, 15, 0));
        appointment.setPriority(Priority.MEDIUM);
        appointment.setStatus(Status.SCHEDULED);

        categoryRepository.save(category);
        appointment.setCategoryEntity(category);

        AppointmentEntity savedAppointment = appointmentRepository.save(appointment);

        assertNotNull(savedAppointment.getId());
    }
}