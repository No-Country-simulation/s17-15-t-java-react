package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record OwnerRequest(
    @NotBlank(message = "El name no puede ir vacio")
    @Size(min = 3, message = "la longitud debe estar entre 5 a 20 caracteres")
    String name,

    @NotBlank(message = "El lastname no puede ir vacio")
    @Size(min = 4, message = "la longitud debe estar entre 5 a 20 caracteres")
    String lastname,

    @NotBlank(message = "El phone no puede ir vacio")
    @Pattern(regexp = "^[0-9]{10,}$", message = "El telefono debe tener un formato valido de minimo 10 número entre 0 y 9")
    String phone,

    @NotBlank(message = "El email no puede ir vacio")
    @Email(message = "El email debe tener un formato valido")
    String email,

    @NotBlank(message = "El address no puede ir vacio")
    @Size(min = 10, message = "la longitud debe estar entre 10 a 255")
    String address
) {
}