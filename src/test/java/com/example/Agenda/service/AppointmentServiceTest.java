package com.example.Agenda.service;

import com.example.Agenda.Enums.Priority;
import com.example.Agenda.Enums.Status;
import com.example.Agenda.Model.AppointmentEntity;
import com.example.Agenda.Model.CategoryEntity;
import com.example.Agenda.Repository.AppointmentRepository;
import com.example.Agenda.Repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    AppointmentService appointmentService;

    @Test
    void saveAppointment() {
        CategoryEntity category = new CategoryEntity(
                UUID.fromString("14ed8a26-c601-40a1-b4e2-984ec2ca5400"),
                "Futebol"
        );

        when(categoryRepository.findById(category.getId()))
                .thenReturn(Optional.of(category));

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setTitle("Treino");
        appointment.setDescription("Treino de futebol");
        appointment.setStartDateTime(LocalDateTime.of(2026, 7, 20, 19, 0));
        appointment.setEndDateTime(LocalDateTime.of(2026, 7, 20, 21, 0));
        appointment.setPriority(Priority.MEDIUM);
        appointment.setStatus(Status.SCHEDULED);
        appointment.setCategoryEntity(category);

        when(appointmentRepository.save(
                any(AppointmentEntity.class))).thenReturn(appointment);

        appointmentService.saveAppointment(appointment);
        verify(appointmentRepository).save(appointment);
    }

    @Test
    void findAll() {
        CategoryEntity category = new CategoryEntity(
                UUID.fromString("14ed8a26-c601-40a1-b4e2-984ec2ca5400"),
                "Futebol"
        );


        AppointmentEntity appointment1 = new AppointmentEntity();
        appointment1.setTitle("Treino");
        appointment1.setDescription("Treino de futebol");
        appointment1.setStartDateTime(LocalDateTime.of(2026, 7, 20, 19, 0));
        appointment1.setEndDateTime(LocalDateTime.of(2026, 7, 20, 21, 0));
        appointment1.setPriority(Priority.MEDIUM);
        appointment1.setStatus(Status.SCHEDULED);
        appointment1.setCategoryEntity(category);

        AppointmentEntity appointment2 = new AppointmentEntity();
        appointment2.setTitle("Competição");
        appointment2.setDescription("Competição de Volei");
        appointment2.setStartDateTime(LocalDateTime.of(2026, 8, 20, 19, 0));
        appointment2.setEndDateTime(LocalDateTime.of(2026, 8, 20, 21, 0));
        appointment2.setPriority(Priority.MEDIUM);
        appointment2.setStatus(Status.SCHEDULED);
        appointment2.setCategoryEntity(category);

        List<AppointmentEntity> appointmentEntityList = Arrays.asList(appointment1, appointment2);

        when(appointmentRepository.findAll()).thenReturn(appointmentEntityList);

        List<AppointmentEntity> appointmentList = appointmentService.findAll();

        assertNotNull(appointmentList);
        Assertions.assertEquals(2, appointmentList.size(), "The appointment list size should be 2");
        Assertions.assertEquals("Treino", appointmentList.get(0).getTitle());
        Assertions.assertEquals("Competição", appointmentList.get(1).getTitle());

    }

    @Test
    void findById() {
        CategoryEntity category = new CategoryEntity(
                UUID.fromString("14ed8a26-c601-40a1-b4e2-984ec2ca5400"),
                "Futebol"
        );


        AppointmentEntity appointment1 = new AppointmentEntity();
        appointment1.setTitle("Treino");
        appointment1.setDescription("Treino de futebol");
        appointment1.setStartDateTime(LocalDateTime.of(2026, 7, 20, 19, 0));
        appointment1.setEndDateTime(LocalDateTime.of(2026, 7, 20, 21, 0));
        appointment1.setPriority(Priority.MEDIUM);
        appointment1.setStatus(Status.SCHEDULED);
        appointment1.setCategoryEntity(category);

        when(appointmentRepository.findById(appointment1.getId()))
                .thenReturn(Optional.of(appointment1));

        AppointmentEntity appointmentEntity = appointmentService.findById(appointment1.getId());

        assertNotNull(appointmentEntity);
        Assertions.assertEquals("Treino", appointmentEntity.getTitle());
    }
}