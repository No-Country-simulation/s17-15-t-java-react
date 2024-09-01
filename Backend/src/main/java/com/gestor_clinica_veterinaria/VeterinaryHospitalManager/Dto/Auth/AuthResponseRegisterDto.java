package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponseRegisterDto(String username,
                                      String message,
                                      String jwt,
                                      boolean status) {
}
