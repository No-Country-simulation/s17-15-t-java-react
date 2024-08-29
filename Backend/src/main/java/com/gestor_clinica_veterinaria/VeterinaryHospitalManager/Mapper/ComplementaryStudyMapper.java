package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.ComplementaryStudyDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.ComplementaryStudy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComplementaryStudyMapper {

    public ComplementaryStudy toEntity(ComplementaryStudyDto dto){
        ComplementaryStudy study = new ComplementaryStudy();
        study.setExaminationDate(dto.examinationDate());
        study.setStudyType(dto.studyType());
        study.setStudyResult(dto.studyResult());
        study.setStudyState(dto.studyState());
        study.setStudyFile(dto.studyFile());
        study.setStudyCost(dto.studyCost());
        study.setDiagnosis(dto.dianosis());
        //study.setConsultation(dto.consultation());
        //study.setHospitalization
        return study;
    }

    public ComplementaryStudyDto toDto(ComplementaryStudy entity) {
        return new ComplementaryStudyDto(
                entity.getExaminationDate(),
                entity.getStudyType(),
                entity.getStudyResult(),
                entity.getStudyState(),
                entity.getStudyFile(),
                entity.getStudyCost(),
                entity.getDiagnosis()
        );
    }
//agregar a las lineas 36 y 37  cuando las entidades estén creadas->
    //entity.getConsultation(),
    //entity.getHospitalization(),
    public List<ComplementaryStudyDto> toDtoList(List<ComplementaryStudy>  studyList){
        return studyList.stream().map(this::toDto).toList();
    }
}
