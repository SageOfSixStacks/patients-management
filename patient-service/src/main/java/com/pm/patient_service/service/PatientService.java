package com.pm.patient_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.patient_service.dtos.PatientResponseDto;

@Service
public interface PatientService {
  List<PatientResponseDto> getPatients();
}