package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.DiagnosticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
                    ),
                    @ApiResponse(
                            responseCode = "201",
                            description = "Diagnostic created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DiagnosticDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid Diagnostic data",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = "application/json")
                    )
            }

    )
    public ResponseEntity<DiagnosticDto> addDiagnostic(@RequestBody DiagnosticDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnosticService.addDiagnostic(dto));

    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all Diagnostics with Pagination",
            description = "Retrieve a paginated list of Diagnostics.",
            tags = {"Diagnostic"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Page of diagnostics retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class) // Indica que la respuesta es un objeto paginado
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No diagnostics found"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = "application/json")
                    )
            },
            parameters = {
                    @Parameter(
                            name = "page",
                            description = "Page number to retrieve (zero-based index)",
                            example = "0",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", defaultValue = "0")
                    ),
                    @Parameter(
                            name = "size",
                            description = "Number of records per page",
                            example = "10",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", defaultValue = "10")
                    )
            }
    )
    public ResponseEntity<List<DiagnosticDto>> getAllDiagnostics( @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(diagnosticService.getAllDiagnostics(page, size));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Diagnostic by ID",
            description = "Retrieve a Diagnostic by its ID",
            tags = {"Diagnostic"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Diagnostic retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DiagnosticDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Diagnostic not found",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid ID supplied",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<DiagnosticDto> getDiagnosticById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosticService.getDiagnosticById(id));
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update Diagnostic",
            description = "Update an existing Diagnostic",
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
                            description = "Diagnostic updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DiagnosticDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Diagnostic not found",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid ID supplied or Diagnostic data is invalid",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<DiagnosticDto> updateDiagnostic(@PathVariable Long id, @RequestBody DiagnosticDto dto) {
        return ResponseEntity.ok(diagnosticService.updateDiagnostic(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Diagnostic",
            description = "Delete a Diagnostic by its ID",
            tags = {"Diagnostic"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Diagnostic deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Diagnostic not found",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid ID supplied",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<Void> deleteDiagnostic(@PathVariable Long id) {
        diagnosticService.deleteDiagnostic(id);
        return ResponseEntity.ok().build();
    }
}
