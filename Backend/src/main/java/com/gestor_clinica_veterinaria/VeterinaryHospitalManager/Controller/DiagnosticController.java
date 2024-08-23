package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.DiagnosticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnostic")
@RequiredArgsConstructor
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    @PostMapping("/add")
    public ResponseEntity<DiagnosticDto> addDiagnostic(@RequestBody DiagnosticDto dto) {
        return ResponseEntity.ok(diagnosticService.addDiagnostic(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiagnosticDto>> getAllDiaggravnostics() {
        return ResponseEntity.ok(diagnosticService.getAllDiagnostics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticDto> getDiagnosticById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosticService.getDiagnosticById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DiagnosticDto> updateDiagnostic(@PathVariable Long id, @RequestBody DiagnosticDto dto) {
        return ResponseEntity.ok(diagnosticService.updateDiagnostic(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDiagnostic(@PathVariable Long id) {
        diagnosticService.deleteDiagnostic(id);
        return ResponseEntity.ok().build();
    }
}
