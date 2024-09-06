package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
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
                description = "Treatment object with fields:  treatmentDescription, duration, adicionalObservations, treatmentCost, dianosis, and possible a list of hospitalizations",
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
                            schema = @Schema(implementation = TreatmentDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<Treatment> addTreatment(@RequestBody TreatmentDto dto) {
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
    public ResponseEntity<List<Treatment>> getAllTreatments() {
        return ResponseEntity.ok(treatmentService.getAllTreatments());
    }

    @GetMapping("/{treatmentId}")
    @Operation(
            summary = "Get Treatment by id",
            description = "Get Treatment by treatment id",
            tags = {"Treatment"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Treatment not found",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )

    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long treatmentId) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(treatmentId));
    }

    @GetMapping("/pet/{petId}")
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
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No treatments found for the pet",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )

    public ResponseEntity<List<Treatment>> getTreatmentsByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(treatmentService.getAllTreatmentsByPetId(petId));
    }

    @GetMapping("/owner/{ownerId}")
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
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No treatments found for the owner",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )

    public ResponseEntity<List<Treatment>> getTreatmentsByOwnerId(@PathVariable Long ownerId) {
        return ResponseEntity.ok(treatmentService.getAllTreatmentsByOwnerId(ownerId));
    }

    @PatchMapping("/update/{treatmentId}")
    @Operation(
            summary = "Update Treatment",
            description = "Partially or fully update a Treatment",
            tags = {"Treatment"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Treatment object with fields to update",
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
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Treatment not found",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long treatmentId, @RequestBody TreatmentDto dto) {
        return ResponseEntity.ok(treatmentService.updateTreatment(treatmentId, dto));
    }
}