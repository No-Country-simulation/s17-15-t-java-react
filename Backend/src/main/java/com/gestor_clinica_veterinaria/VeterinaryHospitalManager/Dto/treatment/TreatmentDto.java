package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
public record TreatmentDto(
        @NotBlank(message = "debe dar una descipción al tratamiento.")
        String treatmentDescription,
        @NotBlank(message = "debe dar una duración al tratamiento.")
        String duration,
        String aditionalObservations,

        BigDecimal treatmentCost,
        @NotBlank(message = "debe proporcionar el id del diagnostico para que se genera el tratamiento.")
        Long diagnosisId,

        Long hospitalizationId
) {
}