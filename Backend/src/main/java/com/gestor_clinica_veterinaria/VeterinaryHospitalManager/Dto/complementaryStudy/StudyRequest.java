package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Schema(description = "Request object for adding a complementary study")
public record StudyRequest(
        @NotNull(message = "El estudio debe contener una fecha correspondiente al día que se ejecuta el mismo, con este formato: yyyy-MM-dd")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @Schema(description = "Date of the examination", example = "2024-09-17")
        LocalDate examinationDate,

        @NotBlank(message = "El estudio debe tener un tipo.")
        @Size(max=50, message = "el tipo de estudio no puede tener más de 50 caracteres")
        @Schema(description = "Type of the study", example = "someType")
        String studyType,

        @Size(max=300, message = "el resultado no puede tener más de 300 caracteres")
        @Schema(description = "Result of the study, it's optional", example = "someResult")
        String studyResult,

        @NotNull(message = "El estudio debe tener un estado.")
        @Schema(description = "State of the study, enum.", example = "PENDIENTE")
        EnumStudyState studyState,

        String studyFile,
        @NotNull(message = "El estudio debe tener un costo.")
        @Schema(description = "Cost of the study", example = "150.00")
        BigDecimal studyCost,

        @Schema(description = "Consultation id relatated with the study", example = "2")
        Long consultationId,

        Long diagnosisId,

        Long  hospitalizationId
    ){
}