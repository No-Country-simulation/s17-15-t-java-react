package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation.ConsultationDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ConsultationEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsultationMapper {

    public ConsultationEntity toEntity(ConsultationDto dto) {
        ConsultationEntity entity = new ConsultationEntity();
        entity.setName(dto.name());
        entity.setAnamnesis(dto.anamnesis());
        entity.setConsultationDate(dto.consultationDate());
        entity.setCostConsultation(dto.costConsultation());
        entity.setObservations(dto.observations());
        entity.setState(dto.state());
        return entity;
    }

    public ConsultationDto toDto(ConsultationEntity entity) {
        return new ConsultationDto(
                entity.getName(),
                entity.getConsultationDate(),
                entity.getAnamnesis(),
                entity.getObservations(),
                entity.getState(),
                entity.getCostConsultation()
        );
    }
}
