package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
public record TreatmentDto(
        LocalDate startDate,
        LocalDate endDate,
        String treatmentDescription,
        String duration,
        String aditionalObservations,
        BigDecimal treatmentCost,
        Long id_diagnosis
) {



}
