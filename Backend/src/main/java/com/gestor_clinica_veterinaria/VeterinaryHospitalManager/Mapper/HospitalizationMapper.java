package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HospitalizationMapper {

    public Hospitalization toEntity(HospitalizationRequest dto){
        Hospitalization hospitalization = new Hospitalization();
        hospitalization.setStartDate(dto.startDate());
        hospitalization.setEnd_date(dto.endDate());
        hospitalization.setPaid(dto.paid());
        hospitalization.setHospitalizationCost(dto.hospitalizationCost());
        return hospitalization;
    }
    public HospitalizationRequest toDto(Hospitalization entity) {
        return new HospitalizationRequest(
                entity.getStartDate(),
                entity.getEnd_date(),
                entity.getHospitalizationCost(),
                entity.isPaid(),
                entity.getTreatments() != null && !entity.getTreatments().isEmpty() ?
                        entity.getTreatments().get(0).getId() : null
        );
    }

    public List<HospitalizationRequest> toDtoList(List<Hospitalization> hospitalizationList) {
        return hospitalizationList.stream().map(this::toDto).toList();
    }
}
