package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import java.math.BigDecimal;

public record TreatmentResponse(
        Long id,
        String treatmentDescription,
        String duration,
        String additionalObservations,
        BigDecimal treatmentCost,
        DiagnosticEntity diagnosis,
        Hospitalization hospitalization
) {}
