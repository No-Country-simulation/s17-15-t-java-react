package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HospitalizationDto(

        @NotBlank(message = "la hospitalización debe tener una fecha de inicio.")
        LocalDate startDate,

        @NotBlank(message = "la hospitalización debe tener una fecha de finalización.")
        LocalDate endDate,

        BigDecimal hospitalizationCost,

        boolean paid,

        @NotBlank(message = "la hospitalización debe estar vinculada con un tratamiento")
        Long treatmentId
) {
}
