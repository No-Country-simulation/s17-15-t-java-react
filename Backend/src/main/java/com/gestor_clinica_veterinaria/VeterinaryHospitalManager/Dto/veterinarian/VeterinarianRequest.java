package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VeterinarianRequest(

        @NotBlank(message = "El campo name no puede ir vacio")
        @Size(min = 3, max = 45)
        String name,

        @NotBlank(message = "El campo lastname no puede ir vacio")
        @Size(min = 3, max = 45)
        String lastname,

        @NotBlank(message = "El campo specialty no puede ir vacio")
        @Size(min = 3, max = 45)
        String specialty,

        @NotBlank(message = "El campo license no puede ir vacio")
        @Size(min = 2, max = 15)
        String professionalLicenceNumber

) {
}
