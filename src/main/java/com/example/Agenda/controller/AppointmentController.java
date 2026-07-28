package com.example.Agenda.controller;

import com.example.Agenda.Model.AppointmentEntity;
import com.example.Agenda.controller.dto.AppointmentDTO;
import com.example.Agenda.controller.dto.CategoryDTO;
import com.example.Agenda.controller.mapper.AppointmentMapper;
import com.example.Agenda.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Void> createAppointment(@RequestBody AppointmentDTO appointmentDTO){
        AppointmentEntity appointmentEntity = AppointmentMapper.toEntity(appointmentDTO);
        appointmentService.saveAppointment(appointmentEntity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(appointmentEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{appointmentId}/category/{categoryId}")
    public ResponseEntity<AppointmentDTO> addingCategoryToAppointment(@PathVariable("appointmentId") String idAppointment,
                                                                      @PathVariable("categoryId") String idCategory ){

        AppointmentEntity appointmentEntity = appointmentService.addingCategoryToAppointment(idAppointment, idCategory);

        return ResponseEntity.ok(AppointmentMapper.toDTO(appointmentEntity));
    }

    @GetMapping("{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable("id") String id){
        var idAppointment = UUID.fromString(id);
        AppointmentEntity appointmentEntity = appointmentService.findById(idAppointment);
        return ResponseEntity.ok(AppointmentMapper.toDTO(appointmentEntity));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointment(){
        List<AppointmentDTO> appointmentDTOList = appointmentService.findAll()
                .stream()
                .map(AppointmentMapper::toDTO)
                .toList();

        if (appointmentDTOList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(appointmentDTOList);
    }
}