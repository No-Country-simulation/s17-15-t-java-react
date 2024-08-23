package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Diagnostic;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions.DiagnosticNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.DiagnosticMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;
    private final DiagnosticMapper diagnosticMapper;

    public DiagnosticDto addDiagnostic(DiagnosticDto dto) {
        Diagnostic diagnostico = diagnosticMapper.toEntity(dto);
        diagnostico = diagnosticRepository.save(diagnostico);
        return diagnosticMapper.toDto(diagnostico);
    }

    public List<DiagnosticDto> getAllDiagnostics() {
        List<Diagnostic> diagnosticList = diagnosticRepository.findAll();
        return diagnosticMapper.toDtoList(diagnosticList);
    }

    public DiagnosticDto getDiagnosticById(Long id) {
        Diagnostic diagnostic = diagnosticRepository.findById(id).orElseThrow(() -> new DiagnosticNotFoundException("El diagnostico buscado no existe"));
        return diagnosticMapper.toDto(diagnostic);
    }

    public DiagnosticDto updateDiagnostic(Long id, DiagnosticDto dto) {
        Diagnostic diagnostic = diagnosticRepository.findById(id).orElseThrow(() -> new DiagnosticNotFoundException("El diagnostico no se puede actualizar porque no existe"));
        diagnostic = diagnosticMapper.toEntity(dto);
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
