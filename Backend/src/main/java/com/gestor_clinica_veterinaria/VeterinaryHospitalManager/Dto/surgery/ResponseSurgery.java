package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ResponseSurgery(

        Long id,
        LocalDate dateSurgery,
        String surgeryType,
        String surgeryProcedure,
        String observations,
        String postSurgeryRecommendations,
        BigDecimal surgeryCost
      //  Long consultationId // Solo se usa para asociar la cirugía a una consulta existente
) {
}
