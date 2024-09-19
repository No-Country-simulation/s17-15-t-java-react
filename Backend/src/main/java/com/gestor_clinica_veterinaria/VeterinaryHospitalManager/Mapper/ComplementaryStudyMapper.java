package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.*;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ConsultationRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.HospitalizationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ComplementaryStudyMapper {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DiagnosticRepository diagnosticEntityRepository;

    @Autowired
    private HospitalizationRepository hospitalizationRepository;

    public ComplementaryStudy toEntity(StudyRequest dto){
        ComplementaryStudy study = new ComplementaryStudy();
        study.setExaminationDate(dto.examinationDate());
        study.setStudyType(dto.studyType());
        study.setStudyState(dto.studyState());
        study.setStudyCost(dto.studyCost());

        if (dto.consultationId() != null) {
            ConsultationEntity consultation = consultationRepository.findById(dto.consultationId())
                    .orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada con ID: " + dto.consultationId()));
            study.setConsultation(consultation);
        }

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

    public StudyRequest toDto(ComplementaryStudy entity) {
        return new StudyRequest(
                entity.getExaminationDate(),
                entity.getStudyType(),
                entity.getStudyResult(),
                entity.getStudyState(),
                entity.getStudyFile(),
                entity.getStudyCost(),
                entity.getConsultation() != null ? entity.getConsultation().getId() : null,
                entity.getDiagnosis().getId() ,
                entity.getHospitalization() != null ? entity.getHospitalization().getId() : null
        );
    }
    public StudyResponse toDtoResponse(ComplementaryStudy entity) {
        return new StudyResponse(
                entity.getId(),
                entity.getExaminationDate(),
                entity.getStudyType(),
                entity.getStudyResult(),
                entity.getStudyFile(),
                entity.getStudyState(),
                entity.getStudyCost(),
                entity.getConsultation(),
                entity.getDiagnosis(),
                entity.getHospitalization()
        );
    }

    public List<StudyResponse> toDtoList(List<ComplementaryStudy>  studyList){
        return studyList.stream()
                .map(study-> toDtoResponse(study))
                        .collect(Collectors.toList());
    }

//    public StudyResponse toDtoResponseByPet(ComplementaryStudy entity) {
//        return new StudyResponse(
//                entity.getId(),
//                entity.getExaminationDate(),
//                entity.getStudyType(),
//                entity.getStudyResult(),
//                entity.getStudyFile(),
//                entity.getStudyState(),
//                entity.getStudyCost(),
//                entity.getConsultation(),
//                entity.getHospitalization()
//        );
//    }

//    public List<StudyResponse> toDtoListByPet(List<ComplementaryStudy> studyList) {
//        return studyList.stream()
//                .map(this::toDtoResponseByPet) // Usa el nuevo método para cada estudio
//                .collect(Collectors.toList());
//    }
}