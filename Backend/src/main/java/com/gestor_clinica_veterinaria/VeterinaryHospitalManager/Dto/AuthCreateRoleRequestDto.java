package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public record AuthCreateRoleRequestDto(@Size(min = 1) List<String> roles) {
}
