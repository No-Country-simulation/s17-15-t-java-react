package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDto(@NotBlank(message = "Username is required") String username,
                                  @NotBlank(message = "Password is required") String password) {
}
