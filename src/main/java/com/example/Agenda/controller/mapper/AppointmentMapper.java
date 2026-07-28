package com.example.Agenda.controller.mapper;

import com.example.Agenda.Model.AppointmentEntity;
import com.example.Agenda.controller.dto.AppointmentDTO;

public class AppointmentMapper {

    private AppointmentMapper() {
    }

    public static AppointmentEntity toEntity(AppointmentDTO dto) {
        AppointmentEntity appointment = new AppointmentEntity();

        appointment.setTitle(dto.title());
        appointment.setDescription(dto.description());
        appointment.setStartDateTime(dto.startDateTime());
        appointment.setEndDateTime(dto.endDateTime());
        appointment.setPriority(dto.priority());
        appointment.setStatus(dto.status());
        appointment.setCategoryEntity(dto.categoryEntity());

        return appointment;
    }

    public static AppointmentDTO toDTO(AppointmentEntity appointment) {
        return new AppointmentDTO(
                appointment.getTitle(),
                appointment.getDescription(),
                appointment.getStartDateTime(),
                appointment.getEndDateTime(),
                appointment.getPriority(),
                appointment.getStatus(),
                appointment.getCategoryEntity()
        );
    }
}