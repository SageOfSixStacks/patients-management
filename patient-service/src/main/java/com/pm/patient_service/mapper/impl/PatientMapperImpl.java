package com.pm.patient_service.mapper.impl;

import org.springframework.stereotype.Component;

import com.pm.patient_service.dtos.PatientResponseDto;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;

@Component
public class PatientMapperImpl implements PatientMapper {

  @Override
  public PatientResponseDto toDto(Patient patient) {
    return new PatientResponseDto(
        patient.getId(),
        patient.getName(),
        patient.getEmail(),
        patient.getAddress(),
        patient.getDateOfBirth());
  }
}
