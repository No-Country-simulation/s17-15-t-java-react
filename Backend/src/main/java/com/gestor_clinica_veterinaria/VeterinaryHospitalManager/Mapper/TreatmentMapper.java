package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TreatmentMapper {

    public Treatment toEntity(TreatmentDto dto){
        Treatment treatment = new Treatment();
        treatment.setStartDate(dto.startDate());
        treatment.setEndDate(dto.endDate());
        treatment.setTreatmentDescription(dto.treatmentDescription());
        treatment.setDuration(dto.duration());
        treatment.setAditionalObservations(dto.aditionalObservations());
        treatment.setTreatmentCost(dto.treatmentCost());
        treatment.setIdDiagnosis(dto.id_diagnosis());
        return treatment;
    }

    public TreatmentDto toDto(Treatment entity) {
        return new TreatmentDto(
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getTreatmentDescription(),
                entity.getDuration(),
                entity.getAditionalObservations(),
                entity.getTreatmentCost(),
                entity.getIdDiagnosis()
        );
    }

    public List<TreatmentDto> toDtoList(List<Treatment> treatmentList) {
        return treatmentList.stream().map(this::toDto).toList();
    }
}
