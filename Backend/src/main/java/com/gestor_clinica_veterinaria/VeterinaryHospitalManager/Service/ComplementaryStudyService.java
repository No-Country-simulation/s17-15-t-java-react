package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyCreatedResponse;
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
import org.springframework.web.multipart.MultipartFile;
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
    private final FileStorageService fileStorageService;


        @Transactional
        public StudyCreatedResponse addComplementaryStudy(StudyRequest studyRequest, MultipartFile file){
            try {
                ComplementaryStudy study = complementaryStudyMapper.toEntity(studyRequest);

                if (file != null && !file.isEmpty()) {
                    String uploadedFileUrl = fileStorageService.saveFile(file);
                    study.setStudyFile(uploadedFileUrl);
                }

                if (studyRequest.hospitalizationId() != null) {
                    Hospitalization hospitalization = hospitalizationRepository.findById(studyRequest.hospitalizationId().get())
                            .orElseThrow(() -> new EntityNotFoundException("Hospitalization not found with id: " + studyRequest.hospitalizationId()));
                    study.setHospitalization(hospitalization);
                    hospitalization.getComplementaryStudies().add(study);
                    hospitalizationRepository.save(hospitalization);
                }

                if (studyRequest.diagnosisId() != null) {
                    DiagnosticEntity diagnosis = diagnosisRepository.findById(studyRequest.diagnosisId().get())
                            .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + studyRequest.diagnosisId()));
                    study.setDiagnosis(diagnosis);
                    diagnosis.getComplementaryStudies().add(study);
                    diagnosisRepository.save(diagnosis);
                }

                if (studyRequest.consultationId() != null) {
                    ConsultationEntity consultation = consultationRepository.findById(studyRequest.consultationId().get())
                            .orElseThrow(() -> new EntityNotFoundException("Consultation not found with id: " + studyRequest.consultationId()));
                    study.setConsultation(consultation);
                    consultation.getComplementaryStudies().add(study);
                    consultationRepository.save(consultation);
                }

                study = complementaryStudyRepository.save(study);

                System.out.println(study);
                if (study.getId() == null) {
                    return new StudyCreatedResponse("Error al crear el estudio complementario.", null);
                }

                return new StudyCreatedResponse("¡El estudio complementario se registró exitosamente!", study.getId());

            } catch (Exception e) {
                return new StudyCreatedResponse("Error: " + e.getMessage(), null);
            }
        }



    public List<ComplementaryStudy> getAllComplementaryStudies(){
        return complementaryStudyRepository.findAll();
    }
    public ComplementaryStudy getStudyById(Long studyId){
        return complementaryStudyRepository.findById(studyId).orElseThrow(() -> new ComplementaryStudyNotFoundException("El id del estudio complementario ingresao es incorrecto o no existe"));
    }

    public List<ComplementaryStudy> getAllStudiesByPetId(Long petId){
        return complementaryStudyRepository.findAllById(Collections.singleton(petId));
    }
    public List<ComplementaryStudy> getAllStudiesByOwnerId(Long ownerId){
        return complementaryStudyRepository.findAllById(Collections.singleton(ownerId));
    }
    public List<ComplementaryStudy> getAllStudiesByState(EnumStudyState state) {
        return complementaryStudyRepository.findByStudyState(state);
    }
    public ComplementaryStudy updateStudy(Long studyId, StudyRequest dto, MultipartFile studyFile) {
        Optional<ComplementaryStudy> studyOptional = complementaryStudyRepository.findById(studyId);

        if (studyOptional.isPresent()){
            ComplementaryStudy existingStudy = studyOptional.get();

            existingStudy.setExaminationDate(dto.examinationDate());
            existingStudy.setStudyCost(dto.studyCost());
            existingStudy.setStudyState(dto.studyState());
            existingStudy.setStudyResult(dto.studyResult().get());
            existingStudy.setStudyType(dto.studyType());

            if (studyFile != null) {
                String filePath = fileStorageService.saveFile(studyFile);
                existingStudy.setStudyFile(filePath);
            }else {
                existingStudy.setStudyFile(null);
            }

            if (dto.consultationId() != null) {
                ConsultationEntity consultation = consultationRepository.findById(dto.consultationId().get())
                        .orElseThrow(() -> new EntityNotFoundException("Consultation not found with id: " + dto.consultationId()));
                existingStudy.setConsultation(consultation);
            } else {
                existingStudy.setConsultation(null);
            }

            if (dto.diagnosisId() != null) {
                DiagnosticEntity diagnosisEntity = diagnosisRepository.findById(dto.diagnosisId().get())
                        .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + dto.diagnosisId()));
                existingStudy.setDiagnosis(diagnosisEntity);
            } else {
                existingStudy.setDiagnosis(null);
            }

            if (dto.hospitalizationId() != null) {
                Hospitalization hospitalization = hospitalizationRepository.findById(dto.hospitalizationId().get())
                        .orElseThrow(() -> new EntityNotFoundException("Hospitalization not found with id: " + dto.hospitalizationId()));
                existingStudy.setHospitalization(hospitalization);
            } else {
                existingStudy.setHospitalization(null);
            }

                return complementaryStudyRepository.save(existingStudy);
        } else {
            throw new TreatmentNotFoundException("No se ha podido actualizar el tratamiento porque el id ingresado es incorrecto o no existe: " + studyId);
        }
        }

}