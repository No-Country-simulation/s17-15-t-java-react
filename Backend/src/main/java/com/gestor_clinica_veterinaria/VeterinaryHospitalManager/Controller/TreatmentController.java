package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentCreationResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentRequest;
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
                            schema = @Schema(implementation = TreatmentRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful treatment created",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TreatmentCreationResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<TreatmentCreationResponse> addTreatment(@RequestBody TreatmentRequest dto) {
        return ResponseEntity.ok(treatmentService.addTreatment(dto));
    }



    @GetMapping("/all")
    @Operation(
            summary = "Get all Treatments",
            description = "Get all treatments in the system",
            tags = {"Treatment"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful action",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<TreatmentResponse>> getAllTreatments() {
        return ResponseEntity.ok(treatmentService.getAllTreatments());
    }


    @GetMapping("/{treatmentId}")
    @Operation(
            summary = "Get Treatment by ID",
            description = "Get a specific treatment by its ID",
            tags = {"Treatment"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful action",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Treatment not found",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<TreatmentResponse> getTreatmentById(@PathVariable Long treatmentId) {
        TreatmentResponse treatment = treatmentService.getTreatmentById(treatmentId);
        return ResponseEntity.ok(treatment);
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
                                    schema = @Schema(implementation = TreatmentResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No treatments found for the owner",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )

    public ResponseEntity<List<TreatmentResponse>> getTreatmentsByOwnerId(@PathVariable Long ownerId) {
        return ResponseEntity.ok(treatmentService.getAllTreatmentsByOwnerId(ownerId));
    }

    @PutMapping("/update/{treatmentId}")
    @Operation(
            summary = "Update Treatment",
            description = "Fully update a Treatment",
            tags = {"Treatment"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Treatment object with fields to update",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TreatmentRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Treatment Successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Treatment not found",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<TreatmentResponse> updateTreatment(@PathVariable Long treatmentId, @RequestBody TreatmentRequest dto) {
        return ResponseEntity.ok(treatmentService.updateTreatment(treatmentId, dto));
    }

    @GetMapping("/byPet/{petId}")
    @Operation(
            summary = "Get Treatments by Pet ID",
            description = "Get all treatments related to a specific Pet ID",
            tags = {"Treatment"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful action",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pet not found",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<TreatmentResponse>> getTreatmentsByPetId(@PathVariable Long petId) {
        List<TreatmentResponse> treatmentResponses = treatmentService.getTreatmentsByPetId(petId);
        return ResponseEntity.ok(treatmentResponses);
    }
}