package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ConsultationEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
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
        ConsultationEntity consultation,
        DiagnosticEntity diagnosis,
        Hospitalization hospitalization
    ){

}