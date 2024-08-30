package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DiagnosticMapper {

    DiagnosticEntity toEntity(DiagnosticDto dto);
    DiagnosticDto toDto(DiagnosticEntity entity);


}
