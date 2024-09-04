package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.TreatmentDto;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;
     private final DiagnosticRepository diagnosisRepository;

    public TreatmentDto addTreatment(TreatmentDto dto){
        Treatment treatment = treatmentMapper.toEntity(dto);

        DiagnosticEntity diagnosisEntity = diagnosisRepository.findById(dto.diagnosisId())
                .orElseThrow(()-> new EntityNotFoundException("Diagnosis not found wwith id: " + dto.diagnosisId()));

        treatment.setDiagnosis(diagnosisEntity);
        treatment = treatmentRepository.save(treatment);
        diagnosisEntity.getTreatments().add(treatment);
        return treatmentMapper.toDto(treatment);
    }

    public List<TreatmentDto> getAllTreatments(){
        List<Treatment> treatmentList = treatmentRepository.findAll();
        return  treatmentMapper.toDtoList(treatmentList);
    }
    public TreatmentDto getTreatmentById(Long treatmentId){
        Treatment treatment = treatmentRepository.findById(treatmentId).orElseThrow(() -> new TreatmentNotFoundException("El id del tratamiento ingresao es incorrecto o no existe"));
        return treatmentMapper.toDto(treatment);
    }
    public List<TreatmentDto> getAllTreatmentsByPetId(Long petId){
        List<Treatment> treatmentList = treatmentRepository.findAllById(Collections.singleton(petId));
        return  treatmentMapper.toDtoList(treatmentList);
    }
    public List<TreatmentDto> getAllTreatmentsByOwnerId(Long ownerId){
        List<Treatment> treatmentList = treatmentRepository.findAllById(Collections.singleton(ownerId));
        return  treatmentMapper.toDtoList(treatmentList);
    }

    public TreatmentDto updateTreatment(Long treatmentId, TreatmentDto dto){

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
                existingTreatment.setAditionalObservations(dto.aditionalObservations());
            }
            if (dto.treatmentCost() != null){
                existingTreatment.setTreatmentCost(dto.treatmentCost());
            }
//            if (dto.diagnosis() != null){
//                existingTreatment.setDiagnosis(dto.diagnosis());
//            }
//            if (dto.hospitalization() != null){
//                existingTreatment.setHospitalization(dto.hospitalization());
//            }

            Treatment updatedTreatment =  treatmentRepository.save(existingTreatment);
            return treatmentMapper.toDto(updatedTreatment);

        } else{
           throw  new  TreatmentNotFoundException("No se ha podido actualizar el tratamiento porque el id ingresado es incorrecto o no existe: " + treatmentId);
        }
    }

    public void deleteTreatmentById(Long treatmentId){
        if (!treatmentRepository.existsById(treatmentId)){
            throw new TreatmentNotFoundException("El tratamiento que que pretende eliminar no existe o es incorrecto veritfique el id ingresado: " + treatmentId);
        }else {
            treatmentRepository.deleteById(treatmentId);
        }
    }
}
