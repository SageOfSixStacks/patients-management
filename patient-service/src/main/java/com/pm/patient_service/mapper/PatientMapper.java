package com.pm.patient_service.mapper;

import com.pm.patient_service.dtos.PatientResponseDto;
import com.pm.patient_service.model.Patient;

public interface PatientMapper {
  PatientResponseDto toDto(Patient patient);
}