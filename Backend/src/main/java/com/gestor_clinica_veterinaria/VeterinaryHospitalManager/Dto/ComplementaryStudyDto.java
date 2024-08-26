package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.EnumStudyState;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.EnumStudyType;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

public record ComplementaryStudyDto (

    LocalDate examinationDate,
    EnumStudyType studyType,
    String studyResult,
    EnumStudyState studyState,
    byte[] studyFile,
    Double studyCost,
    Long idDianosis,
    Long idConsultation
){
}
