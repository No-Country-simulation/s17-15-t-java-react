package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.ComplementaryStudyDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.ComplementaryStudyMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ComplementaryStudyRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.HospitalizationRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.ComplementaryStudyNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.TreatmentNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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

    public ComplementaryStudy addComplementaryStudy(ComplementaryStudyDto dto){
        ComplementaryStudy study = complementaryStudyMapper.toEntity(dto);
        return complementaryStudyRepository.save(study);
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
    public ComplementaryStudy updateStudy(Long studyId, ComplementaryStudyDto dto){

        Optional<ComplementaryStudy> studyOptional = complementaryStudyRepository.findById(studyId);

        if (studyOptional.isPresent()) {

            ComplementaryStudy existingStudy = studyOptional.get();

            if (dto.examinationDate() != null){
                existingStudy.setExaminationDate(dto.examinationDate());
            }
            if (dto.studyCost() != null){
                existingStudy.setStudyCost(dto.studyCost());
            }
            if (dto.studyState() != null){
                existingStudy.setStudyState(dto.studyState());
            }
            if (dto.studyResult() != null){
                existingStudy.setStudyResult(dto.studyResult());
            }
            if (dto.studyFile() != null){
                existingStudy.setStudyFile(dto.studyFile());
            }
//            if (dto.consultation() != null){
//                existingStudy.setConsultation(dto.consultation());
//            }
            if (dto.diagnosisId() != null) {
                DiagnosticEntity diagnosisEntity = diagnosisRepository.findById(dto.diagnosisId())
                        .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + dto.diagnosisId()));
                existingStudy.setDiagnosis(diagnosisEntity);
            }
            if (dto.studyType() != null){
                existingStudy.setStudyType(dto.studyType());
            }

            if (dto.hospitalizationId() != null) {
                Hospitalization hospitalization = hospitalizationRepository.findById(dto.hospitalizationId())
                        .orElseThrow(() -> new EntityNotFoundException("Hospitalization not found with id: " + dto.hospitalizationId()));
                existingStudy.setHospitalization(hospitalization);
            } else {
                existingStudy.setHospitalization(null);
            }

            return  complementaryStudyRepository.save(existingStudy);

        } else{
            throw  new  TreatmentNotFoundException("No se ha podido actualizar el tratamiento porque el id ingresado es incorrecto o no existe: " + studyId);
        }
    }

}
