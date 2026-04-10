package com.pm.patient_service.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record PatientRequestDto(
    @NotBlank(message = ERROR_MESSAGE_NAME_LENGTH) @Size(max = 100, message = ERROR_MESSAGE_NAME_LENGTH) String name,
    @NotBlank(message = ERROR_MESSAGE_EMAIL_LENGTH) @Email(message = ERROR_MESSAGE_EMAIL_VALID) String email,
    @NotBlank(message = ERROR_MESSAGE_ADDRESS_LENGTH) String address,
    @PastOrPresent(message = ERROR_MESSAGE_DATEOFBIRTH_PAST) LocalDate dateOfBirth) {
  private static final String ERROR_MESSAGE_NAME_LENGTH = "Name must be between 1 to 100 characters";
  private static final String ERROR_MESSAGE_EMAIL_LENGTH = "Email must be provided";
  private static final String ERROR_MESSAGE_EMAIL_VALID = "Email must be valid";
  private static final String ERROR_MESSAGE_ADDRESS_LENGTH = "Address must be provided";
  private static final String ERROR_MESSAGE_DATEOFBIRTH_PAST = "Date of birth must not be in the future";
}