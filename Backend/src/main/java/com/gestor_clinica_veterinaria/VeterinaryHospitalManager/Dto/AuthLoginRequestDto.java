package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDto(@NotBlank String username,
                                  String password) {
}
