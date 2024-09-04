package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumState;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ConsultationDto(
    String name,
    LocalDate consultationDate,
    String anamnesis,
    String observations,
    EnumState state,
    BigDecimal costConsultation
) {
}
