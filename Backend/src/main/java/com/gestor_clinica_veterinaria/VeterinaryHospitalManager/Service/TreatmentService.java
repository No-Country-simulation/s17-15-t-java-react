package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentCreationResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentRequest;
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

    public TreatmentCreationResponse addTreatment(TreatmentRequest treatmentRequest){
        try{
            Treatment treatment = treatmentMapper.toEntity(treatmentRequest);

            if (treatmentRequest.diagnosisId() != null){
                DiagnosticEntity diagnosisEntity = diagnosisRepository.findById(treatmentRequest.diagnosisId().get())
                        .orElseThrow(() -> new EntityNotFoundException("Hospitalization not found with id: " + treatmentRequest.diagnosisId()));
                treatment.setDiagnosis(diagnosisEntity);
                diagnosisEntity.getTreatments().add(treatment);
                diagnosisRepository.save(diagnosisEntity);
            }

            if (treatmentRequest.hospitalizationId() != null){
                Hospitalization hospitalization = hospitalizationRepository.findById(treatmentRequest.hospitalizationId().get())
                        .orElseThrow(()-> new EntityNotFoundException("Hospitalization not found with id: " + treatmentRequest.hospitalizationId()));
                treatment.setHospitalization(hospitalization);
                hospitalization.getTreatments().add(treatment);
                hospitalizationRepository.save(hospitalization);
            }

            treatment = treatmentRepository.save(treatment);

            if (treatment.getId() == null) {
                return new TreatmentCreationResponse("Error al crear el tratamiento.", null);
            }

            return new TreatmentCreationResponse("¡Tratamiento creado exitosamente!", treatment.getId());
        }catch (Exception e) {
            return new TreatmentCreationResponse("Error: " + e.getMessage(), null);
        }

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

    public Treatment updateTreatment(Long treatmentId, TreatmentRequest dto) {
        Treatment existingTreatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new TreatmentNotFoundException("No se ha podido actualizar el tratamiento porque el id ingresado es incorrecto o no existe: " + treatmentId));

        existingTreatment.setTreatmentDescription(dto.treatmentDescription());
        existingTreatment.setDuration(dto.duration());
        existingTreatment.setAdditionalObservations(dto.aditionalObservations());
        existingTreatment.setTreatmentCost(dto.treatmentCost());

        if (dto.diagnosisId() != null) {
            DiagnosticEntity diagnosisEntity = diagnosisRepository.findById(dto.diagnosisId().get())
                    .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + dto.diagnosisId()));
            existingTreatment.setDiagnosis(diagnosisEntity);
        } else {
            existingTreatment.setDiagnosis(null);
        }

        if (dto.hospitalizationId() != null) {
            Hospitalization hospitalization = hospitalizationRepository.findById(dto.hospitalizationId().get())
                    .orElseThrow(() -> new EntityNotFoundException("Hospitalization not found with id: " + dto.hospitalizationId()));
            existingTreatment.setHospitalization(hospitalization);
        } else {
            existingTreatment.setHospitalization(null);
        }

        return treatmentRepository.save(existingTreatment);
    }
}