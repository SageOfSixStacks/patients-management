package com.pm.patient_service.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pm.patient_service.dtos.CreatePatientRequestDto;
import com.pm.patient_service.dtos.PatientResponseDto;
import com.pm.patient_service.exceptions.EmailAlreadyExitsException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import com.pm.patient_service.service.PatientService;

@Service
public class PatientSeviceImpl implements PatientService {

  private final PatientRepository patientRepository;
  private final PatientMapper patientMapper;

  public PatientSeviceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
    this.patientRepository = patientRepository;
    this.patientMapper = patientMapper;
  }

  // Method to handle all patient list retrieval
  @Override
  public List<PatientResponseDto> getPatients() {
    List<Patient> patients = patientRepository.findAll(Sort.by(Direction.ASC, "registeredDate"));
    List<PatientResponseDto> patientResponseDtos = patients.stream().map(patientMapper::toDto).toList();
    return patientResponseDtos;
  }

  // Method to handle patient creation
  @Override
  public PatientResponseDto createPatient(CreatePatientRequestDto createPatientRequestDto) {

    if (patientRepository.existsByEmail(createPatientRequestDto.email())) {
      throw new EmailAlreadyExitsException(
          String.format("A patient with this email %s already exists", createPatientRequestDto.email()));
    }

    Instant now = Instant.now();

    Patient patient = new Patient(
        null,
        createPatientRequestDto.name(),
        createPatientRequestDto.email(),
        createPatientRequestDto.address(),
        createPatientRequestDto.dateOfBirth(),
        now);

    PatientResponseDto patientResponseDto = patientMapper.toDto(patientRepository.save(patient));

    return patientResponseDto;
  }

}
