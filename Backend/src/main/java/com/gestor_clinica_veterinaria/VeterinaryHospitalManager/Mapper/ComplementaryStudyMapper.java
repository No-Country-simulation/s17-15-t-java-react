package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.ComplementaryStudyDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.HospitalizationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ComplementaryStudyMapper {
    //@Autowired
    //private ConsultationRepository consultationRepository;

    @Autowired
    private DiagnosticRepository diagnosticEntityRepository;

    @Autowired
    private HospitalizationRepository hospitalizationRepository;

    public ComplementaryStudy toEntity(ComplementaryStudyDto dto){
        ComplementaryStudy study = new ComplementaryStudy();
        study.setExaminationDate(dto.examinationDate());
        study.setStudyType(dto.studyType());
        study.setStudyResult(dto.studyResult());
        study.setStudyState(dto.studyState());
        study.setStudyFile(dto.studyFile());
        study.setStudyCost(dto.studyCost());

//        if (dto.consultationId() != null) {
//            Consultation consultation = consultationRepository.findById(dto.consultationId())
//                    .orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada con ID: " + dto.consultationId()));
//            study.setConsultation(consultation);
//        }

        if (dto.diagnosisId() != null) {
            DiagnosticEntity diagnosis = diagnosticEntityRepository.findById(dto.diagnosisId())
                    .orElseThrow(() -> new EntityNotFoundException("Diagnóstico no encontrado con ID: " + dto.diagnosisId()));
            study.setDiagnosis(diagnosis);
        }

        if (dto.hospitalizationId() != null) {
            Hospitalization hospitalization = hospitalizationRepository.findById(dto.hospitalizationId())
                    .orElseThrow(() -> new EntityNotFoundException("Hospitalización no encontrada con ID: " + dto.hospitalizationId()));
            study.setHospitalization(hospitalization);
        }
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
                entity.getDiagnosis() != null ? entity.getDiagnosis().getId() : null,
                entity.getHospitalization() != null ? entity.getHospitalization().getId() : null
        );
    }
    //entity.getConsultation() != null ? entity.getConsultation().getId() : null,

    public List<ComplementaryStudyDto> toDtoList(List<ComplementaryStudy>  studyList){
        return studyList.stream().map(this::toDto).toList();
    }
}
