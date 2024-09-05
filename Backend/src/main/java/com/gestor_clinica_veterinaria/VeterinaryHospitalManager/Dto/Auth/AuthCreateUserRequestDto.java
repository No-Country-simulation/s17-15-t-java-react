package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequestDto(@NotBlank String username,
                                       @NotBlank String lastName,
                                       @NotBlank @Email String email,
                                       @NotBlank String specialty,
                                       @NotBlank String professionalLicenceNumber,
                                       @NotBlank String password,
                                       @Valid AuthCreateRoleRequestDto roleDto) {
}