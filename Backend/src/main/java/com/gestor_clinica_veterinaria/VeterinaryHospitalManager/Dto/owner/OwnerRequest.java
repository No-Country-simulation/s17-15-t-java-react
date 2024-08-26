package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record OwnerRequest(
    @NotBlank(message = "El name no puede ir vacio")
    @Size(min = 5, max = 20, message = "la longitud debe estar entre 5 a 20 caracteres")
    String name,

    @NotBlank(message = "El name no puede ir vacio")
    @Size(min = 5, max = 20, message = "la longitud debe estar entre 5 a 20 caracteres")
    String lastname,

    @NotBlank(message = "El name no puede ir vacio")
    @Pattern(regexp = "^\\d{13}$", message = "El telefono debe tener un formato valido")
    String phone,

    @NotBlank(message = "El name no puede ir vacio")
    @Email(message = "El email debe tener un formato valido")
    String email,

    @NotBlank(message = "El name no puede ir vacio")
    @Size(min = 10, max = 255, message = "la longitud debe estar entre 10 a 255")
    String address
) {

}
