package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions.DiagnosticNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.DiagnosticMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;
    private final DiagnosticMapper diagnosticMapper;

    @Transactional
    public DiagnosticDto addDiagnostic(DiagnosticDto dto) {
        try {
            DiagnosticEntity diagnostico = diagnosticMapper.toEntity(dto);
            diagnostico = diagnosticRepository.save(diagnostico);
            return diagnosticMapper.toDto(diagnostico);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error de integridad de datos al agregar el diagnóstico", e);
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al agregar el diagnóstico", e);
        }
    }

    public Page<DiagnosticDto> getAllDiagnostics(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<DiagnosticEntity> diagnosticPage = diagnosticRepository.findAll(pageable);

        return diagnosticPage.map(diagnosticMapper::toDto);
    }

    public DiagnosticDto getDiagnosticById(Long id) {
        DiagnosticEntity diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new DiagnosticNotFoundException("El diagnostico buscado no existe"));
        return diagnosticMapper.toDto(diagnostic);
    }

    @Transactional
    public DiagnosticDto updateDiagnostic(Long id, DiagnosticDto dto) {
        DiagnosticEntity diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new DiagnosticNotFoundException("El diagnóstico no se puede actualizar porque no existe"));

        diagnostic.setDateDiagnostic(dto.dateDiagnostic());
        diagnostic.setDescription(dto.description());
        diagnostic.setSeveridad(dto.severidad());
        diagnostic.setNextControlDate(dto.nextControlDate());

        diagnostic = diagnosticRepository.save(diagnostic);
        return diagnosticMapper.toDto(diagnostic);
    }

    @Transactional
    public void deleteDiagnostic(Long id) {
        try {
            diagnosticRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new DiagnosticNotFoundException("El diagnóstico no se puede eliminar porque no existe" + ex);
        }
    }

}
