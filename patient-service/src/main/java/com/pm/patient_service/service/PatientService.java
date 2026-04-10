package com.pm.patient_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pm.patient_service.dtos.PatientRequestDto;
import com.pm.patient_service.dtos.PatientResponseDto;

@Service
public interface PatientService {
  List<PatientResponseDto> getPatients();

  PatientResponseDto createPatient(PatientRequestDto createPatientRequestDto);

  PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto);

  void deletePatient(UUID id);
}