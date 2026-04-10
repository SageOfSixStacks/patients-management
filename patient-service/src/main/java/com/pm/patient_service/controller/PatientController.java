package com.pm.patient_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patient_service.dtos.CreatePatientRequestDto;
import com.pm.patient_service.dtos.PatientResponseDto;
import com.pm.patient_service.service.PatientService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
  private final PatientService patientService;

  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @GetMapping
  public ResponseEntity<List<PatientResponseDto>> getPatients() {
    return ResponseEntity
        .ok()
        .body(patientService.getPatients());
  }

  @PostMapping
  public ResponseEntity<PatientResponseDto> createPatient(
      @Valid @RequestBody CreatePatientRequestDto patientRequestDto) {
    return ResponseEntity
        .created(null)
        .body(patientService.createPatient(patientRequestDto));
  }
}
