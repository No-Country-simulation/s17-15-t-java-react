package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;
import java.util.Set;

public record UserDto(
        @Email(message = "Email must be valid") @NotBlank(message = "Email must not be blank")
        String email,
        @NotBlank(message = "Username must not be blank")
        String username,
        @NotBlank(message = "Password must not be blank")
        String password,
        Set<String> roles
) {
}
