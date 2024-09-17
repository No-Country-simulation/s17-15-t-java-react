package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment;

import java.math.BigDecimal;

public record TreatmentDto(
        Long id,
        String treatmentDescription,
        String duration,
        String additionalObservations,
        BigDecimal treatmentCost,
        Long diagnosisId,
        Long hospitalizationId
) {}
