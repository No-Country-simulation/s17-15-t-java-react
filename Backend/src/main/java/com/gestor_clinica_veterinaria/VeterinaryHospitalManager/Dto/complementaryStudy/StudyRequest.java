package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record StudyRequest(
        @NotNull(message = "El estudio debe contener una fecha correspondiente al día que se ejecuta el mismo, con este formato: yyyy-MM-dd")
        LocalDate examinationDate,

        @NotBlank(message = "El estudio debe tener un tipo.")
        @Size(max=50, message = "el tipo de estudio no puede tener más de 50 caracteres")
        String studyType,

        @NotBlank(message = "El estudio debe tener un resultado.")
        @Size(max=300, message = "el resultado no puede tener más de 300 caracteres")
        String studyResult,

        @NotNull(message = "El estudio debe tener un estado.")
        EnumStudyState studyState,
        //byte[] studyFile,
        String studyFile,
        @NotNull(message = "El estudio debe tener un costo.")
        BigDecimal studyCost,

        Long consultationId,

        Long diagnosisId,

        Long hospitalizationId
    ){
}