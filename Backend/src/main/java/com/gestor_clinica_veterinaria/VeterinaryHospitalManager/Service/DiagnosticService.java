package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions.DiagnosticNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.DiagnosticMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;
    private final DiagnosticMapper diagnosticMapper;

    public DiagnosticDto addDiagnostic(DiagnosticDto dto) {
        DiagnosticEntity diagnostico = diagnosticMapper.toEntity(dto);
        diagnostico = diagnosticRepository.save(diagnostico);
        return diagnosticMapper.toDto(diagnostico);
    }

    public List<DiagnosticDto> getAllDiagnostics() {
        List<DiagnosticEntity> diagnosticList = diagnosticRepository.findAll();
        return diagnosticMapper.toDtoList(diagnosticList);
    }

    public DiagnosticDto getDiagnosticById(Long id) {
        DiagnosticEntity diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new DiagnosticNotFoundException("El diagnostico buscado no existe"));
        return diagnosticMapper.toDto(diagnostic);
    }

    public DiagnosticDto updateDiagnostic(Long id, DiagnosticDto dto) {
        DiagnosticEntity diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new DiagnosticNotFoundException("El diagnóstico no se puede actualizar porque no existe"));

        diagnostic.setDateDiagnostic(dto.dateDiagnostic());
        diagnostic.setDescription(dto.description());
        diagnostic.setSeveridad(dto.gravedad());
        diagnostic.setNextControlDate(dto.nextControlDate());

        diagnostic = diagnosticRepository.save(diagnostic);
        return diagnosticMapper.toDto(diagnostic);
    }

    public void deleteDiagnostic(Long id) {
        if (!diagnosticRepository.existsById(id)) {
            throw new DiagnosticNotFoundException("El diagnóstico no se puede eliminar porque no existe");
        } else {
            diagnosticRepository.deleteById(id);
        }
    }
}
