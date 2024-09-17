package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentCreationResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.TreatmentMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.TreatmentRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.TreatmentNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;
     private final DiagnosticRepository diagnosisRepository;

    public TreatmentCreationResponse addTreatment(TreatmentRequest treatmentRequest){
        try{
            DiagnosticEntity diagnosis = diagnosisRepository.findById(treatmentRequest.diagnosisId())
                    .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + treatmentRequest.diagnosisId()));

            Treatment treatment = treatmentMapper.toEntity(treatmentRequest, diagnosis);

            treatment = treatmentRepository.save(treatment);

            if (treatment.getId() == null) {
                return new TreatmentCreationResponse("Error al crear el tratamiento.", null);
            }

            return new TreatmentCreationResponse("¡Tratamiento creado exitosamente!", treatment.getId());
        }catch (Exception e) {
            return new TreatmentCreationResponse("Error: " + e.getMessage(), null);
        }

    }
    public List<TreatmentDto> getAllTreatments() {
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Obtener un tratamiento por ID como DTO
    public TreatmentDto getTreatmentById(Long treatmentId) {
        Treatment treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new TreatmentNotFoundException("El id del tratamiento ingresado es incorrecto o no existe"));
        return convertToDto(treatment);
    }

    private TreatmentDto convertToDto(Treatment treatment) {
        return new TreatmentDto(
                treatment.getId(),
                treatment.getTreatmentDescription(),
                treatment.getDuration(),
                treatment.getAdditionalObservations(),
                treatment.getTreatmentCost(),
                treatment.getDiagnosis() != null ? treatment.getDiagnosis().getId() : null,
                treatment.getHospitalization() != null ? treatment.getHospitalization().getId() : null
        );
    }

    // Método para obtener todos los tratamientos por ID de mascota
    public List<TreatmentDto> getTreatmentsByPetId(Long petId) {
        List<Treatment> treatments = treatmentRepository.findTreatmentsByPetId(petId);
        return treatments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
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

        if (!existingTreatment.getDiagnosis().getId().equals(dto.diagnosisId())) {
            DiagnosticEntity diagnosisEntity = diagnosisRepository.findById(dto.diagnosisId())
                    .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + dto.diagnosisId()));
            existingTreatment.setDiagnosis(diagnosisEntity);
        }

        return treatmentRepository.save(existingTreatment);
    }
}