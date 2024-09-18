package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record HospitalizationResponse(
        Long id,
        LocalDate startDate,
        LocalDate end_date,
        BigDecimal hospitalizationCost,
       boolean paid,
        List<ComplementaryStudy> complementaryStudies,
         List<Treatment> treatments
) {
}
