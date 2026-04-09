package com.pm.patient_service.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "created", nullable = false, updatable = false)
  private Instant created;

  @Column(name = "updated", nullable = false)
  private Instant updated;

  public Patient() {
  }

  public Patient(UUID id, String name, String email, String address, LocalDate dateOfBirth, Instant created,
      Instant updated) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.address = address;
    this.dateOfBirth = dateOfBirth;
    this.created = created;
    this.updated = updated;
  }

}