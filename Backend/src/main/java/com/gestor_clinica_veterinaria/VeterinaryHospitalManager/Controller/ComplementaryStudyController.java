package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.ComplementaryStudyDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.ComplementaryStudyService;
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
@RequestMapping("/complementaryStudies")
@RequiredArgsConstructor
@Tag(name="ComplementaryStudy", description = "Endpoints to manage Complementary Studies")
public class ComplementaryStudyController {
    private final ComplementaryStudyService complementaryStudyService;

    @PostMapping("/add")
    @Operation(
            summary = "Add Complementary Study",
            description = "Add a new complementary study",
            tags = {"Complementary Study"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Complementary Study objetct with fields: examinationDate, studyType, studyResult, studyResult, studyState, studyType, studyCost, idConsultation and idDiagnosis",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ComplementaryStudyDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful Complementary study created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ComplementaryStudyController.class)
                            )
                    )
            }
    )
    public ResponseEntity<ComplementaryStudyDto> addStudy(@RequestBody ComplementaryStudyDto dto) {
        return ResponseEntity.ok(complementaryStudyService.addComplementaryStudy(dto));
    }
    @GetMapping("/all")
    @Operation(
            summary = "Get all Complementary studies",
            description = "Get all Complementary studies",
            tags = {"Complementary studies"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful action",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ComplementaryStudyDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ComplementaryStudyDto>> getAllTreatments() {
        return ResponseEntity.ok(complementaryStudyService.getAllComplementaryStudies());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Complementary Study by id",
            description = "Get Complementary Study by id",
            tags = {"Complementary Study"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ComplementaryStudyDto.class)
                            )
                    )
            }
    )

    public ResponseEntity<ComplementaryStudyDto> getComplementaryStudyById(@PathVariable Long studyId) {
        return ResponseEntity.ok(complementaryStudyService.getStudyById(studyId));
    }

    @PatchMapping("/update/{id}")
    @Operation(
            summary = "Update Complementary Study",
            description = "Partially or fully update a Complementary Study",
            tags = {"Complementary Study"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Complementary Study object with fields to update",
                    required = false,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ComplementaryStudyDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Complementary Study Successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TreatmentDto.class)
                            )
                    )
            }
    )
    public ResponseEntity<ComplementaryStudyDto> updateComplementaryStudy(@PathVariable Long studyId, @RequestBody ComplementaryStudyDto dto) {
        return ResponseEntity.ok(complementaryStudyService.updateStudy(studyId, dto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Complementary Study",
            description = "Delete a Complementary Study",
            tags = {"Complementary Study"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Complementary Study errased Successfully "
                    )
            }
    )
    public ResponseEntity<Void> deleteComplementaryStudyById(@PathVariable Long studyId) {
        complementaryStudyService.deleteStudyById(studyId);
        return ResponseEntity.ok().build();
    }
}
