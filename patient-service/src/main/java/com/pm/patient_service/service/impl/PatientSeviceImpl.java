package com.pm.patient_service.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pm.patient_service.dtos.PatientRequestDto;
import com.pm.patient_service.dtos.PatientResponseDto;
import com.pm.patient_service.exceptions.EmailAlreadyExitsException;
import com.pm.patient_service.exceptions.PatientNotFoundException;
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

  // Method to handle all patient records retrieval
  @Override
  public List<PatientResponseDto> getPatients() {
    List<Patient> patients = patientRepository.findAll(Sort.by(Direction.ASC, "registeredDate"));
    List<PatientResponseDto> patientResponseDtos = patients.stream().map(patientMapper::toDto).toList();
    return patientResponseDtos;
  }

  // Method to handle patient record creation
  @Override
  public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {

    if (patientRepository.existsByEmail(patientRequestDto.email())) {
      throw new EmailAlreadyExitsException(
          String.format("A patient with this email: %s already exists", patientRequestDto.email()));
    }

    Instant now = Instant.now();

    Patient patient = new Patient(
        null,
        patientRequestDto.name(),
        patientRequestDto.email(),
        patientRequestDto.address(),
        patientRequestDto.dateOfBirth(),
        now);

    return patientMapper.toDto(patient);
  }

  // Method to handle patient record updates
  @Override
  public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {
    Patient patient = patientRepository.findById(id)
        .orElseThrow(() -> new PatientNotFoundException(String.format("Patient with Id: %s not found", id)));

    if (patientRepository.existsByEmailAndIdNot(patientRequestDto.email(), id)) {
      throw new EmailAlreadyExitsException(
          String.format("Patient with email: %s already exist", patientRequestDto.email()));
    }

    patient.setName(patientRequestDto.name());
    patient.setEmail(patientRequestDto.email());
    patient.setAddress(patientRequestDto.address());
    patient.setDateOfBirth(patientRequestDto.dateOfBirth());

    patientRepository.save(patient);
    return patientMapper.toDto(patient);
  }

  // Method to handle patient record deletions
  @Override
  public void deletePatient(UUID id) {
    patientRepository.findById(id)
        .orElseThrow(() -> new PatientNotFoundException(String.format("Patient with Id: %s not found", id)));
    patientRepository.deleteById(id);
  }

}
