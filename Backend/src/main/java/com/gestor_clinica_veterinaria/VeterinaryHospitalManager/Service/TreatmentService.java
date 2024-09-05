package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.TreatmentMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.HospitalizationRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.TreatmentRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.TreatmentNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;
     private final DiagnosticRepository diagnosisRepository;
     private final HospitalizationRepository hospitalizationRepository;

    public Treatment addTreatment(TreatmentDto dto){
        Treatment treatment = treatmentMapper.toEntity(dto);

        DiagnosticEntity diagnosisEntity = diagnosisRepository.findById(dto.diagnosisId())
                .orElseThrow(()-> new EntityNotFoundException("Diagnosis not found with id: " + dto.diagnosisId()));

        treatment.setDiagnosis(diagnosisEntity);
        treatment = treatmentRepository.save(treatment);
        return treatment;
    }

    public List<Treatment> getAllTreatments(){
        return treatmentRepository.findAll();
    }
    public Treatment getTreatmentById(Long treatmentId){
        return treatmentRepository.findById(treatmentId).orElseThrow(() -> new TreatmentNotFoundException("El id del tratamiento ingresao es incorrecto o no existe"));
    }
    public List<Treatment> getAllTreatmentsByPetId(Long petId){
        return treatmentRepository.findAllById(Collections.singleton(petId));
    }
    public List<Treatment> getAllTreatmentsByOwnerId(Long ownerId){
        return treatmentRepository.findAllById(Collections.singleton(ownerId));
    }

    public Treatment updateTreatment(Long treatmentId, TreatmentDto dto){

        Optional<Treatment> treatmentOptional = treatmentRepository.findById(treatmentId);

        if (treatmentOptional.isPresent()) {

            Treatment existingTreatment = treatmentOptional.get();

            if (dto.treatmentDescription() != null){
                existingTreatment.setTreatmentDescription(dto.treatmentDescription());
            }
            if (dto.duration() != null){
                existingTreatment.setDuration(dto.duration());
            }
            if (dto.aditionalObservations() != null){
                existingTreatment.setAdditionalObservations(dto.aditionalObservations());
            }
            if (dto.treatmentCost() != null){
                existingTreatment.setTreatmentCost(dto.treatmentCost());
            }
            if (dto.diagnosisId() != null) {
                DiagnosticEntity diagnosisEntity = diagnosisRepository.findById(dto.diagnosisId())
                        .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + dto.diagnosisId()));
                existingTreatment.setDiagnosis(diagnosisEntity);
            }
            if (dto.hospitalizationId() != null) {
                Hospitalization hospitalization = hospitalizationRepository.findById(dto.hospitalizationId())
                        .orElseThrow(() -> new EntityNotFoundException("Hospitalization not found with id: " + dto.hospitalizationId()));
                existingTreatment.setHospitalization(hospitalization);
            } else {
                existingTreatment.setHospitalization(null);
            }

            return treatmentRepository.save(existingTreatment);

        } else {
            throw new TreatmentNotFoundException("No se ha podido actualizar el tratamiento porque el id ingresado es incorrecto o no existe: " + treatmentId);
        }
    }
}