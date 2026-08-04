package com.example.Agenda.controller;

import com.example.Agenda.Enums.Priority;
import com.example.Agenda.Enums.Status;
import com.example.Agenda.Model.AppointmentEntity;
import com.example.Agenda.service.AppointmentService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Nested
@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppointmentService appointmentService;

    @Test
    void createAppointment() throws Exception {

        doNothing().when(appointmentService).saveAppointment(any(AppointmentEntity.class));

        mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Dentista",
                                    "description": "Consulta de rotina",
                                    "startDateTime": "2026-12-10T09:00",
                                    "endDateTime": "2026-12-10T10:00",
                                    "priority": "MEDIUM",
                                    "status": "SCHEDULED"
                                }
                                """))
                .andExpect(status().isCreated());

        verify(appointmentService).saveAppointment(any(AppointmentEntity.class));
    }

    @Test
    void getAppointmentById() throws Exception {
        UUID idAppointment = UUID.randomUUID();

        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(idAppointment);
        appointmentEntity.setTitle("Dentista");
        appointmentEntity.setDescription("Consulta de rotina");
        appointmentEntity.setStartDateTime(LocalDateTime.of(2026, 12, 10, 9, 0));
        appointmentEntity.setEndDateTime(LocalDateTime.of(2026, 12, 10, 10, 0));
        appointmentEntity.setPriority(Priority.MEDIUM);
        appointmentEntity.setStatus(Status.SCHEDULED);
        appointmentEntity.setCategoryEntity(null);

        when(appointmentService.findById(idAppointment)).thenReturn(appointmentEntity);

        mockMvc.perform(get("/appointments/{id}", idAppointment)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Dentista"))
                .andExpect(jsonPath("$.description").value("Consulta de rotina"))
                .andExpect(jsonPath("$.priority").value("MEDIUM"))
                .andExpect(jsonPath("$.status").value("SCHEDULED"));
    }

    @Test
    void getAllAppointment() throws Exception {
        AppointmentEntity appointment1 = new AppointmentEntity();
        appointment1.setId(UUID.randomUUID());
        appointment1.setTitle("Dentista");
        appointment1.setDescription("Consulta de rotina");
        appointment1.setStartDateTime(LocalDateTime.of(2026, 12, 10, 9, 0));
        appointment1.setEndDateTime(LocalDateTime.of(2026, 12, 10, 10, 0));
        appointment1.setPriority(Priority.MEDIUM);
        appointment1.setStatus(Status.SCHEDULED);

        AppointmentEntity appointment2 = new AppointmentEntity();
        appointment2.setId(UUID.randomUUID());
        appointment2.setTitle("Treino");
        appointment2.setDescription("Treino de futebol");
        appointment2.setStartDateTime(LocalDateTime.of(2026, 12, 11, 19, 0));
        appointment2.setEndDateTime(LocalDateTime.of(2026, 12, 11, 21, 0));
        appointment2.setPriority(Priority.HIGH);
        appointment2.setStatus(Status.SCHEDULED);

        List<AppointmentEntity> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentService.findAll()).thenReturn(appointments);

        mockMvc.perform(get("/appointments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Dentista"))
                .andExpect(jsonPath("$[1].title").value("Treino"));

        verify(appointmentService).findAll();
    }
}