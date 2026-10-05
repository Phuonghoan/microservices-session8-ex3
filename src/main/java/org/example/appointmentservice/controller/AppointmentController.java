package org.example.appointmentservice.controller;

import org.example.appointmentservice.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getPatient(
            @PathVariable Long patientId
    ) {
        return appointmentService.getPatient(patientId);
    }
}