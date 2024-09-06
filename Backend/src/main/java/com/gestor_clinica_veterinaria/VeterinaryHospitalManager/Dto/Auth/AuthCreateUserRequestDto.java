package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequestDto(@NotBlank(message = "Username cannot be blank") String username,
                                       @NotBlank(message = "Last name cannot be blank") String lastName,
                                       @NotBlank(message = "Email cannot be blank") @Email(message = "Email must be valid") String email,
                                       @NotBlank(message = "Specialty cannot be blank") String specialty,
                                       @NotBlank(message = "Professional licence number cannot be blank") String professionalLicenceNumber,
                                       @NotBlank(message = "Password cannot be blank") String password,
                                       @Valid AuthCreateRoleRequestDto roleDto) {
}