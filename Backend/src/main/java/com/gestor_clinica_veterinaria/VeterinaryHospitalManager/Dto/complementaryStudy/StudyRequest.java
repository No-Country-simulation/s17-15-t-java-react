package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StudyRequest(
        @NotNull(message = "El estudio debe contener una fecha correspondiente al día que se ejecuta el mismo.")
                @DateTimeFormat()
        LocalDate examinationDate,

        @NotBlank(message = "El estudio debe tener un tipo.")
        String studyType,

        @NotBlank(message = "El estudio debe tener un resultado.")
        String studyResult,

        @NotNull(message = "El estudio debe tener un estado.")
        EnumStudyState studyState,
        //byte[] studyFile,
        String studyFile,
        @NotNull(message = "El estudio debe tener un costo.")
        BigDecimal studyCost,

        Long consultationId,

        Long  diagnosisId,

        Long hospitalizationId
    ){
}