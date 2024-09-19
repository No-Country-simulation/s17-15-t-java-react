package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyCreatedResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ConsultationEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.ComplementaryStudyMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ComplementaryStudyRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ConsultationRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.HospitalizationRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.ComplementaryStudyNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.TreatmentNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComplementaryStudyService {

    private final ComplementaryStudyRepository complementaryStudyRepository;
    private final ComplementaryStudyMapper complementaryStudyMapper;
    private final HospitalizationRepository hospitalizationRepository;
    private final DiagnosticRepository diagnosisRepository;
    private final ConsultationRepository consultationRepository;

        @Transactional
        public StudyCreatedResponse addComplementaryStudy(StudyRequest studyRequest)  {
            try {
                ComplementaryStudy study = complementaryStudyMapper.toEntity(studyRequest);

                if (studyRequest.hospitalizationId() != null) {
                    Long hospitalizationId = studyRequest.hospitalizationId();
                    Hospitalization hospitalization = hospitalizationRepository.findById(hospitalizationId)
                            .orElseThrow(() -> new EntityNotFoundException("Hospitalization not found with id: " + hospitalizationId));
                    study.setHospitalization(hospitalization);
                    hospitalization.getComplementaryStudies().add(study);
                    hospitalizationRepository.save(hospitalization);
                }

                if (studyRequest.diagnosisId() != null) {
                    Long diagnosisId = studyRequest.diagnosisId();
                    DiagnosticEntity diagnosis = diagnosisRepository.findById(diagnosisId)
                            .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + diagnosisId));
                    study.setDiagnosis(diagnosis);
                    diagnosis.getComplementaryStudies().add(study);
                    diagnosisRepository.save(diagnosis);
                }

                if (studyRequest.consultationId() != null) {
                    Long consultationId = studyRequest.consultationId();
                    ConsultationEntity consultation = consultationRepository.findById(consultationId)
                            .orElseThrow(() -> new EntityNotFoundException("Consultation not found with id: " + consultationId));
                    study.setConsultation(consultation);
                    consultation.getComplementaryStudies().add(study);
                    consultationRepository.save(consultation);
                }
                study = complementaryStudyRepository.save(study);

                if (study.getId() == null) {
                    return new StudyCreatedResponse("Error al crear el estudio complementario.", null);
                }

                return new StudyCreatedResponse("¡El estudio complementario se registró exitosamente!", study.getId());

            } catch (Exception e) {
                return new StudyCreatedResponse("Error: " + e.getMessage(), null);
            }
        }

    public List<StudyResponse> getAllComplementaryStudies(){
        return complementaryStudyMapper.toDtoList(complementaryStudyRepository.findAll());
    }
    public StudyResponse getStudyById(Long studyId){
        return complementaryStudyMapper.toDtoResponse(complementaryStudyRepository.
                findById(studyId).orElseThrow(() ->
                        new ComplementaryStudyNotFoundException("El id del estudio complementario ingresao es incorrecto o no existe")));
    }

    public List<StudyResponse> getAllStudiesByPetId(Long petId){
        return complementaryStudyMapper.toDtoList(complementaryStudyRepository.findAllById(Collections.singleton(petId)));
    }


    public List<StudyResponse> getAllStudiesByOwnerId(Long ownerId){
        return complementaryStudyMapper.toDtoList(complementaryStudyRepository.findAllById(Collections.singleton(ownerId)));
    }
    public List<StudyResponse> getAllStudiesByState(EnumStudyState state) {
        return  complementaryStudyMapper.toDtoList(complementaryStudyRepository.findByStudyState(state));
    }
    public StudyResponse updateStudy(Long studyId, StudyRequest dto) {
        Optional<ComplementaryStudy> studyOptional = complementaryStudyRepository.findById(studyId);

        if (studyOptional.isPresent()){
            ComplementaryStudy existingStudy = studyOptional.get();

            existingStudy.setExaminationDate(dto.examinationDate());
            existingStudy.setStudyCost(dto.studyCost());
            existingStudy.setStudyFile(dto.studyFile());
            existingStudy.setStudyState(dto.studyState());
            existingStudy.setStudyResult(dto.studyResult());
            existingStudy.setStudyType(dto.studyType());

            if (dto.consultationId() != null) {
                ConsultationEntity consultation = consultationRepository.findById(dto.consultationId())
                        .orElseThrow(() -> new EntityNotFoundException("Consultation not found with id: " + dto.consultationId()));
                existingStudy.setConsultation(consultation);
            } else {
                existingStudy.setConsultation(null);
            }

            if (dto.diagnosisId() != null) {
                DiagnosticEntity diagnosisEntity = diagnosisRepository.findById(dto.diagnosisId())
                        .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + dto.diagnosisId()));
                existingStudy.setDiagnosis(diagnosisEntity);
            } else {
                existingStudy.setDiagnosis(null);
            }

            if (dto.hospitalizationId() != null) {
                Hospitalization hospitalization = hospitalizationRepository.findById(dto.hospitalizationId())
                        .orElseThrow(() -> new EntityNotFoundException("Hospitalization not found with id: " + dto.hospitalizationId()));
                existingStudy.setHospitalization(hospitalization);
            } else {
                existingStudy.setHospitalization(null);
            }

            return complementaryStudyMapper.toDtoResponse(complementaryStudyRepository.save(existingStudy));
        } else {
            throw new TreatmentNotFoundException("No se ha podido actualizar el tratamiento porque el id ingresado es incorrecto o no existe: " + studyId);
        }
        }
}