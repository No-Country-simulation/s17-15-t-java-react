package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.TreatmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatment")
@RequiredArgsConstructor
@Tag(name="Treatment", description = "Endpoints to manage Treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    @PostMapping("/add")
    @Operation(
            summary = "Add Treatment",
            description = "Add a new treatment",
            tags = {"Treatment"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Treatment objetct with fields:  treatmentDescription, duration, adicionalObservations, treatmentCost, dianosis, and possible a list of hospitalizations",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TreatmentDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful treatment created",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TreatmentController.class)
                            )
                    )
            }
    )
    public ResponseEntity<TreatmentDto> addTreatment(@RequestBody TreatmentDto dto) {
        return ResponseEntity.ok(treatmentService.addTreatment(dto));
    }
    @GetMapping("/all")
    @Operation(
            summary = "Get all Treatments",
            description = "Get all Treatments",
            tags = {"Treatment"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful action",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<TreatmentDto>> getAllTreatments() {
        return ResponseEntity.ok(treatmentService.getAllTreatments());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Treatment by id",
            description = "Get Treatment by id",
            tags = {"Treatment"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentDto.class)
                            )
                    )
            }
    )

    public ResponseEntity<TreatmentDto> getTreatmentById(@PathVariable Long id) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(id));
    }
    @GetMapping("/{petId}")
    @Operation(
            summary = "Get Treatment by pet.",
            description = "Get Treatment by pet id.",
            tags = {"Treatment"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentDto.class)
                            )
                    )
            }
    )

    public ResponseEntity<List<TreatmentDto>> getTreatmentsByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(treatmentService.getAllTreatmentsByPetId(petId));
    }

    @GetMapping("/{ownerId}")
    @Operation(
            summary = "Get Treatments by owner.",
            description = "Get Treatments by owner id.",
            tags = {"Treatment"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentDto.class)
                            )
                    )
            }
    )

    public ResponseEntity<List<TreatmentDto>> getTreatmentsByOwnerId(@PathVariable Long ownerId) {
        return ResponseEntity.ok(treatmentService.getAllTreatmentsByOwnerId(ownerId));
    }

    @PatchMapping("/update/{id}")
    @Operation(
            summary = "Update Treatment",
            description = "Partially or fully update a Treatment",
            tags = {"Treatment"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Treatment object with fields to update",
                    required = false,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TreatmentDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Treatment Successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<TreatmentDto> updateTreatment(@PathVariable Long id, @RequestBody TreatmentDto dto) {
        return ResponseEntity.ok(treatmentService.updateTreatment(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Treatment",
            description = "Delete a Treatment",
            tags = {"Treatment"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Treatment errased Successfully "
                    )
            }
    )
    public ResponseEntity<Void> deleteTreatmentById(@PathVariable Long treatmentId) {
        treatmentService.deleteTreatmentById(treatmentId);
        return ResponseEntity.ok().build();
    }
}
