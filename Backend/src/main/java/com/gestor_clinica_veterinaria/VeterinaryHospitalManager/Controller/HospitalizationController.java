package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.HospitalizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hospitalization")
@RequiredArgsConstructor
@Tag(name = "Hospitalization", description = "Endpoints to manage Hospitalizations")
public class HospitalizationController {

    private final HospitalizationService hospitalizationService;

    @PostMapping("/add")
    @Operation(
            summary = "Add Hospitalization",
            description = "Add a new hospitalization report",
            tags = {"Hospitalization"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Hospitalization object with fields: startDate, endDate and in case the client paid immediately the field 'paid' in true ",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = HospitalizationDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful hospitalization created",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HospitalizationDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<Hospitalization> addHospitalization(@RequestBody HospitalizationDto dto){
        return ResponseEntity.ok(hospitalizationService.addHospitalization(dto));
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all Hospitalizations",
            description = "Get all hospitalizations",
            tags = {"Hospitalization"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful action",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HospitalizationDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<Hospitalization>> getAllHospitalizations() {
        return ResponseEntity.ok(hospitalizationService.getAllHospitalizations());
    }

    @GetMapping("/{hospitalizationId}")
    @Operation(
            summary = "Get Hospitalizations by id",
            description = "Get Hospitalizations by hospitalization id",
            tags = {"Hospitalization"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HospitalizationDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Hospitalizations not found",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )

    public ResponseEntity<Hospitalization> getHospitalizationById(@PathVariable Long hospitalizationId) {
        return ResponseEntity.ok(hospitalizationService.getHospitalizationById(hospitalizationId));
    }

    @GetMapping("/treatment/{treatmentId}")
    @Operation(
            summary = "Get Hospitalizations by Treatment",
            description = "Get Hospitalizations associated with a specific treatment",
            tags = {"Hospitalization"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Hospitalization.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No hospitalizations found for the given treatment",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<Hospitalization>> getHospitalizationByTreatment(@PathVariable Long treatmentId) {
        return ResponseEntity.ok(hospitalizationService.getHospitalizationByTreatment(treatmentId));
    }

    @GetMapping("/complementaryStudy/{complementaryStudyId}")
    @Operation(
            summary = "Get Hospitalizations by Complementary Study",
            description = "Get Hospitalizations associated with a specific complementary study",
            tags = {"Hospitalization"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Hospitalization.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No hospitalizations found for the given complementary study",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<Hospitalization>> getHospitalizationByComplementaryStudy(@PathVariable Long complementaryStudyId) {
        return ResponseEntity.ok(hospitalizationService.getHospitalizationByComplementaryStudy(complementaryStudyId));
    }

    @PatchMapping("/update/{hospitalizationId}")
    @Operation(
            summary = "Update Hospitalization",
            description = "Partially or fully update a Hospitalization",
            tags = {"Hospitalization"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Hospitalization object with fields to update",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = HospitalizationDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Hospitalization Successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HospitalizationDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Hospitalization not found",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<Hospitalization> updateHospitalization(@PathVariable Long hospitalizationId, @RequestBody HospitalizationDto dto) {
        return ResponseEntity.ok(hospitalizationService.updateHospitalization(hospitalizationId, dto));
    }
}
