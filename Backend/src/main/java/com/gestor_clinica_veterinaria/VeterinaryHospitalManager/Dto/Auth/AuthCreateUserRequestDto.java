package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequestDto(@NotBlank String username, @NotBlank String email, @NotBlank String password,
                                       @Valid AuthCreateRoleRequestDto roleDto) {
}
