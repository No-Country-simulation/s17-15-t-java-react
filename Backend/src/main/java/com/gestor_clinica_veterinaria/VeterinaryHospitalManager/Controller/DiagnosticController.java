package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.DiagnosticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnostic")
@RequiredArgsConstructor
@Tag(name = "Diagnostic", description = "Endpoints for managing Diagnostics")
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    @PostMapping("/add")
    @Operation(
            summary = "Add Diagnostic",
            description = "Add a new Diagnostic",
            tags = {"Diagnostic"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Diagnostic object with fields dateDiagnostic, description, gravedad, nextControlDate",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DiagnosticDto.class)
                    )
            ),
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successful operation",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = DiagnosticDto.class)
                        )
                )
            }

    )
    public ResponseEntity<DiagnosticDto> addDiagnostic(@RequestBody DiagnosticDto dto) {
        return ResponseEntity.ok(diagnosticService.addDiagnostic(dto));
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all Diagnostics",
            description = "Get all Diagnostics",
            tags = {"Diagnostic"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DiagnosticDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<DiagnosticDto>> getAllDiaggravnostics() {
        return ResponseEntity.ok(diagnosticService.getAllDiagnostics());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Diagnostic by id",
            description = "Get Diagnostic by id",
            tags = {"Diagnostic"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DiagnosticDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<DiagnosticDto> getDiagnosticById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosticService.getDiagnosticById(id));
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update Diagnostic",
            description = "Update a Diagnostic",
            tags = {"Diagnostic"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Diagnostic object with fields dateDiagnostic, description, gravedad, nextControlDate",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DiagnosticDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DiagnosticDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<DiagnosticDto> updateDiagnostic(@PathVariable Long id, @RequestBody DiagnosticDto dto) {
        return ResponseEntity.ok(diagnosticService.updateDiagnostic(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Diagnostic",
            description = "Delete a Diagnostic",
            tags = {"Diagnostic"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation"
                    )
            }
    )
    public ResponseEntity<Void> deleteDiagnostic(@PathVariable Long id) {
        diagnosticService.deleteDiagnostic(id);
        return ResponseEntity.ok().build();
    }
}
