package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.TreatmentMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.TreatmentRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.TreatmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;

    public TreatmentDto addTreatment(TreatmentDto dto){
        Treatment treatment = treatmentMapper.toEntity(dto);
        treatment = treatmentRepository.save(treatment);
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

    public TreatmentDto updateTreatment(Long treatmentId, TreatmentDto dto){

        Optional<Treatment> treatmentOptional = treatmentRepository.findById(treatmentId);

        if (treatmentOptional.isPresent()) {

            Treatment existingTreatment = treatmentOptional.get();

            if (dto.getStartDate() != null){
                existingTreatment.setStartDate(dto.getStartDate());
            }
            if (dto.getEndDate() != null){
                existingTreatment.setEndDate(dto.getEndDate());
            }
            if (dto.getTreatmentDescription() != null){
                existingTreatment.setTreatmentDescription(dto.getTreatmentDescription());
            }
            if (dto.getDuration() != null){
                existingTreatment.setDuration(dto.getDuration());
            }
            if (dto.getAditionalObservations() != null){
                existingTreatment.setAditionalObservations(dto.getAditionalObservations());
            }
            if (dto.getTreatmentCost() != null){
                existingTreatment.setTreatmentCost(dto.getTreatmentCost());
            }
            if (dto.getId_diagnosis() != null){
                existingTreatment.setIdDiagnosis(dto.getId_diagnosis());
            }

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
