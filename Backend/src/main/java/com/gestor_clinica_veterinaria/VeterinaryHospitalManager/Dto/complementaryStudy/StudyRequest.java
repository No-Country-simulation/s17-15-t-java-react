package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public record StudyRequest(
        @NotNull(message = "El estudio debe contener una fecha correspondiente al día que se ejecuta el mismo, con este formato: yyyy-MM-dd")
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate examinationDate,

        @NotBlank(message = "El estudio debe tener un tipo.")
        @Size(max=50, message = "el tipo de estudio no puede tener más de 50 caracteres")
        String studyType,

        @Size(max=300, message = "el resultado no puede tener más de 300 caracteres")
        Optional<String> studyResult,

        @NotNull(message = "El estudio debe tener un estado.")
        EnumStudyState studyState,

        Optional<String> studyFile,
        @NotNull(message = "El estudio debe tener un costo.")
        BigDecimal studyCost,

        Optional<Long> consultationId,

        Optional<Long> diagnosisId,

        Optional<Long> hospitalizationId
    ){
}