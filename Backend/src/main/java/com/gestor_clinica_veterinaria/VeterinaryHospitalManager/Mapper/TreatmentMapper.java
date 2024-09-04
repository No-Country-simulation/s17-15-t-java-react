package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TreatmentMapper {

    public Treatment toEntity(TreatmentDto dto){
        Treatment treatment = new Treatment();
        treatment.setTreatmentDescription(dto.treatmentDescription());
        treatment.setDuration(dto.duration());
        treatment.setAdditionalObservations(dto.aditionalObservations());
        treatment.setTreatmentCost(dto.treatmentCost());
        if (dto.hospitalizationId() != null) {
            Hospitalization hospitalization = new Hospitalization();
            hospitalization.setId(dto.hospitalizationId());
            treatment.setHospitalization(hospitalization);
        }
        return treatment;
    }

    public TreatmentDto toDto(Treatment entity) {
        return new TreatmentDto(
                entity.getTreatmentDescription(),
                entity.getDuration(),
                entity.getAdditionalObservations(),
                entity.getTreatmentCost(),
                entity.getDiagnosis() != null ? entity.getDiagnosis().getId() : null,
                entity.getHospitalization() != null ? entity.getHospitalization().getId() : null
        );
    }


    public List<TreatmentDto> toDtoList(List<Treatment> treatmentList) {
        return treatmentList.stream().map(this::toDto).toList();
    }
}
