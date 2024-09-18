package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import java.math.BigDecimal;
import java.time.LocalDate;

public record StudyResponse(
        Long id,
        LocalDate examinationDate,
        String studyType,
        String studyResult,
        String studyFile,
        EnumStudyState studyState,
        BigDecimal studyCost,
        Long consultationId,
        Long diagnosisId,
        Long  hospitalizationId
    ){

}