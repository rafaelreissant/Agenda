package com.example.Agenda.service;

import com.example.Agenda.Model.AppointmentEntity;
import com.example.Agenda.Model.CategoryEntity;
import com.example.Agenda.Repository.AppointmentRepository;
import com.example.Agenda.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

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
        if (appointment.getEndDateTime().isBefore(appointment.getStartDateTime())){
            throw new IllegalArgumentException("End date/time must be after start date/time.");
        }

        CategoryEntity category = categoryRepository.findById(appointment.getCategoryEntity().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        appointment.setCategoryEntity(category);
        appointmentRepository.save(appointment);
    }

    public List<AppointmentEntity> findAll(){
        return appointmentRepository.findAll();
    }
    
    public AppointmentEntity findById(UUID id){
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
}