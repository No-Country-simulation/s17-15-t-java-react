package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "username", "message", "jwt", "status"})
public record AuthResponseDto(Long id,
                              String username,
                              String message,
                              String jwt,
                              boolean status) {
}
