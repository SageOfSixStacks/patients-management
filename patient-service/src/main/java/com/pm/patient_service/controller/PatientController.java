package com.pm.patient_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patient_service.dtos.PatientRequestDto;
import com.pm.patient_service.dtos.PatientResponseDto;
import com.pm.patient_service.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/patients")
@Tag(name = "Patient", description = "An API for managing Patients")
public class PatientController {
  private final PatientService patientService;

  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @GetMapping
  @Operation(summary = "Get Patients")
  public ResponseEntity<List<PatientResponseDto>> getPatients() {
    return ResponseEntity
        .ok()
        .body(patientService.getPatients());
  }

  @PostMapping
  @Operation(summary = "Create a new Patient")
  public ResponseEntity<PatientResponseDto> createPatient(
      @Valid @RequestBody PatientRequestDto patientRequestDto) {
    return ResponseEntity
        .created(null)
        .body(patientService.createPatient(patientRequestDto));
  }

  @PutMapping("/{patientId}")
  @Operation(summary = "Update a Patient")
  public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID patientId,
      @Valid @RequestBody PatientRequestDto patientRequestDto) {
    return ResponseEntity
        .ok()
        .body(patientService.updatePatient(patientId, patientRequestDto));
  }

  @DeleteMapping("/{patientId}")
  @Operation(summary = "Delete a Patient")
  public ResponseEntity<Void> deletePatient(@PathVariable UUID patientId) {
    patientService.deletePatient(patientId);
    return ResponseEntity
        .noContent()
        .build();
  }
}
