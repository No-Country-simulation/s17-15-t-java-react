package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RequestCreateSurgery(
     //   @NotNull(message = "dateDiagnostic cannot be null")
     //   @Past(message = "dateDiagnostic must be a date before the current date")
     //   LocalDate dateDiagnostic,
//
     //   @NotNull(message = "surgeryType cannot be null")
     //   @Size(min = 2, max = 50, message = "surgeryType must be between 2 and 50 characters in length")
     //   String surgeryType,
//
     //   @NotNull(message = "surgeryProcedure cannot be null")
     //   @Size(min = 2, max = 100, message = "surgeryProcedure must be between 2 and 100 characters in length")
     //   String surgeryProcedure,
//
     //   @Size(max = 255, message = "observations must be a maximum of 255 characters in length")
     //   String observations,
//
     //   @Size(max = 255, message = "postSurgeryRecommendations must be a maximum of 255 characters in length")
     //   String postSurgeryRecommendations,
//
     //   @NotNull(message = "surgeryCost cannot be null")
     //   @Positive(message = "surgeryCost must be a positive number")
     //   Double surgeryCost

      //  @NotNull(message = "consultationId cannot be null")
      //  Long consultationId
     LocalDate dateSurgery,
     String surgeryType,
     String surgeryProcedure,
     String observations,
     String postSurgeryRecommendations,
     BigDecimal surgeryCost
     //Long consultationId


) {
}
