package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record HospitalizationDtoResponse(

        Long id,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal hospitalizationCost,
        boolean paid,
        List<Long> treatmentIds, // Lista de IDs de tratamientos
        List<Long> complementaryStudies // Lista de IDs de estudios complementario

) {
}
