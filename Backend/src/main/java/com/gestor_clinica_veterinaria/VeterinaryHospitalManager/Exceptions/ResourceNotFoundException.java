package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
