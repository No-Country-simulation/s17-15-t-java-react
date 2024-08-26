package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.EnumStudyState;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.EnumStudyType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

@Getter
@Setter
public record ComplementaryStudyDto (

    LocalDate examinationDate,
    EnumStudyType studyType,
    String studyResult,
    EnumStudyState studyState,
    MultipartFile studyFile,
    Double studyCost,
    Long idDianosis,
    Long idConsultation
){
}
