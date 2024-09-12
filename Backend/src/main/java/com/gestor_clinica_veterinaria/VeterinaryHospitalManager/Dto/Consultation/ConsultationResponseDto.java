package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ConsultationResponseDto(
        @NotNull(message = "You must provide an id of the veterinarian") Long id_veterinarian,
        @NotNull(message = "You must provide an id of the pet ") Long id_pet,
        @NotBlank(message = "Name cannot be blank") String name,
        @NotNull(message = "Consultation date cannot be null") LocalDate consultationDate,
        @NotBlank(message = "Anamnesis cannot be blank") String anamnesis,
        @NotBlank(message = "Observations cannot be blank") String observations,
        @NotNull(message = "State cannot be null") EnumState state,
        BigDecimal costConsultation,
        Long id_consultation
) {
}
