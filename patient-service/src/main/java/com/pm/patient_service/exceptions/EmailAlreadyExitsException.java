package com.pm.patient_service.exceptions;

public class EmailAlreadyExitsException extends RuntimeException {
  public EmailAlreadyExitsException(String message) {
    super(message);
  }
}