package org.example.appointmentservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final PatientService patientService;

    public AppointmentService(PatientService patientService) {
        this.patientService = patientService;
    }

    public ResponseEntity<?> getPatient(Long patientId) {
        return patientService.getPatient(patientId);
    }
}
