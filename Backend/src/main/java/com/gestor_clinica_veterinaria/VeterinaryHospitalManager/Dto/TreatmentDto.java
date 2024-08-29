package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
public record TreatmentDto(
        @NotBlank(message = "debe dar una descipción al tratamiento.")
        String treatmentDescription,

        @NotBlank(message = "debe dar una duración al tratamiento.")
        String duration,

        String aditionalObservations,
        BigDecimal treatmentCost,

        DiagnosticEntity diagnosis
) {
}