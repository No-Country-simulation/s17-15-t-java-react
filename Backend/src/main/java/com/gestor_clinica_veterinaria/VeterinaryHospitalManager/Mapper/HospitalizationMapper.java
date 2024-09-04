package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.HospitalizationDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HospitalizationMapper {

    public Hospitalization toEntity(HospitalizationDto dto){
        Hospitalization hospitalization = new Hospitalization();
        hospitalization.setStartDate(dto.startDate());
        hospitalization.setEnd_date(dto.endDate());
        hospitalization.setPaid(dto.paid());
        hospitalization.setHospitalizationCost(dto.hospitalizationCost());
        return hospitalization;
    }
    public HospitalizationDto toDto(Hospitalization entity) {
        return new HospitalizationDto(
                entity.getStartDate(),
                entity.getEnd_date(),
                entity.getHospitalizationCost(),
                entity.isPaid(),
                entity.getTreatments() != null && !entity.getTreatments().isEmpty() ?
                        entity.getTreatments().get(0).getId() : null
        );
    }

    public List<HospitalizationDto> toDtoList(List<Hospitalization> hospitalizationList) {
        return hospitalizationList.stream().map(this::toDto).toList();
    }
}
