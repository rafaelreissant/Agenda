package com.example.Agenda.service;

import com.example.Agenda.Model.AppointmentEntity;
import com.example.Agenda.Model.CategoryEntity;
import com.example.Agenda.Repository.AppointmentRepository;
import com.example.Agenda.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CategoryRepository categoryRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, CategoryRepository categoryRepository) {
        this.appointmentRepository = appointmentRepository;
        this.categoryRepository = categoryRepository;
    }

    public void saveAppointment(AppointmentEntity appointment){
        if (appointment.getStartDateTime().isEqual(appointment.getEndDateTime())){
            throw new IllegalArgumentException("Start and End can't have the exactly same date");
        }

        if (appointment.getStartDateTime().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Can't appoint a date before today");
        }

        if (appointment.getEndDateTime().isBefore(appointment.getStartDateTime())){
            throw new IllegalArgumentException("End date/time must be after start date/time.");
        }

        appointmentRepository.save(appointment);
    }

    public AppointmentEntity addingCategoryToAppointment(String idAppointment, String idCategory){
        AppointmentEntity appointmentEntity = appointmentRepository.findById(UUID.fromString(idAppointment))
                .orElseThrow(() -> new RuntimeException("Id Appointment not found"));

        CategoryEntity categoryEntity = categoryRepository.findById(UUID.fromString(idCategory))
                .orElseThrow(() -> new RuntimeException("Id Category not found"));

        appointmentEntity.setCategoryEntity(categoryEntity);

        return appointmentRepository.save(appointmentEntity);
    }

    public List<AppointmentEntity> findAll(){
        return appointmentRepository.findAll();
    }
    
    public AppointmentEntity findById(UUID id){
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
}