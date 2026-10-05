package org.example.appointmentservice.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.example.appointmentservice.entity.Patient;
import org.example.appointmentservice.exception.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Service
public class PatientService {

    private final RestClient restClient;

    public PatientService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Retry(
            name = "patientRetry",
            fallbackMethod = "getPatientFallback"
    )
    public ResponseEntity<?> getPatient(Long patientId) {

        Patient patient = restClient.get()
                .uri("http://localhost:8084/api/v1/patients/" + patientId)
                .retrieve()
                .body(Patient.class);

        return ResponseEntity.ok(patient);
    }

    public ResponseEntity<?> getPatientFallback(
            Long patientId,
            Exception e
    ) {

        ApiResponseError error = new ApiResponseError(
                LocalDateTime.now(),
                503,
                "Patient Service Error",
                "Hiện tại không thể kiểm tra thông tin bệnh nhân, vui lòng thử lại sau vài giây."
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(error);
    }
}
