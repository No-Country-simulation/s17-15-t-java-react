package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.FileRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyCreatedResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.File;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.ComplementaryStudyService;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/complementaryStudies")
@RequiredArgsConstructor
@Tag(name="Complementary Study", description = "Endpoints to manage Complementary Studies")
public class ComplementaryStudyController {
    private final ComplementaryStudyService complementaryStudyService;
    private final FileStorageService fileStorageService;

    @PostMapping("/add")
    @Operation(
            summary = "Add a new Complementary Study",
            description = "Add a new complementary study",
            tags = {"Complementary Study"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Complementary Study objetct with fields: examinationDate, studyType, studyResult, studyResult, studyState, studyType, studyCost, consultation and possible or not a diagnosis and/or a hospitalization; to studyState there are some options:\n" +
                            " PENDIENTE, //study is scheduled but has not been performed yet\n" +
                            " EN_PROGRESO, // The study is currently being conducted\n" +
                            " COMPLETADO, //The study has been completed.\n" +
                            " ESPERANDO_RESULTADOS, // The study has been completed, but the results are not yet available.\n" +
                            " RESULTADOS_LISTOS, // The results of the study are ready for review\n" +
                            " REVISADOS, // The results have been reviewed by the veterinarian.\n" +
                            " CANCELADOS, //The study was scheduled but then cancelled.\n" +
                            " FALLIDOS, // The study could not be completed due to technical or other issues.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudyRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful Complementary study created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudyCreatedResponse.class)
                            )
                    )
            }
    )

    public ResponseEntity<StudyCreatedResponse> addStudy(@RequestBody StudyRequest studyRequest, @RequestParam("file") MultipartFile studyFile) {

        String filePath = fileStorageService.saveFile(studyFile);
        FileRequest fileDTO = new FileRequest(studyFile.getOriginalFilename(), filePath, studyFile.getContentType(), studyFile.getSize());
        return ResponseEntity.ok(complementaryStudyService.addComplementaryStudy(studyRequest, studyFile));
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update Complementary Study",
            description = "Fully update a Complementary Study",
            tags = {"Complementary Study"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Complementary Study object with fields to update",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudyRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Complementary Study Successfully updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ComplementaryStudy.class)
                            )
                    )
            }
    )
//    public ResponseEntity<ComplementaryStudy> updateComplementaryStudy(@PathVariable Long studyId, @RequestBody StudyRequest dto) {
//        return ResponseEntity.ok(complementaryStudyService.updateStudy(studyId, dto));
//    }
    public ResponseEntity<ComplementaryStudy> updateComplementaryStudy(@PathVariable Long studyId, @RequestBody StudyRequest dto, @RequestParam("file") MultipartFile studyFile) {
        return ResponseEntity.ok(complementaryStudyService.updateStudy(studyId, dto, studyFile));
    }


    @GetMapping("/all")
    @Operation(
            summary = "Get all Complementary studies",
            description = "Get all Complementary studies",
            tags = {"Complementary Study"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful action",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ComplementaryStudy.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<ComplementaryStudy>> getAllTreatments() {
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
                                    schema = @Schema(implementation = ComplementaryStudy.class)
                            )
                    )
            }
    )
    public ResponseEntity<ComplementaryStudy> getComplementaryStudyById(@PathVariable Long studyId) {
        return ResponseEntity.ok(complementaryStudyService.getStudyById(studyId));
    }

    @GetMapping("/pet/{petId}")
    @Operation(
            summary = "Get all the Complementary Studies by pet.",
            description = "Get all the Complementary Studies by pet id.",
            tags = {"Complementary Study"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ComplementaryStudy.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid pet id value",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<ComplementaryStudy>> getAllStudiesByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(complementaryStudyService.getAllStudiesByPetId(petId));
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(
            summary = "Get all the Complementary Studies by owner.",
            description = "Get all the Complementary Studies  by owner id.",
            tags = {"Complementary Study"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ComplementaryStudy.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid owner id value",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<ComplementaryStudy>> getStudiesByOwnerId(@PathVariable Long ownerId) {
        return ResponseEntity.ok(complementaryStudyService.getAllStudiesByOwnerId(ownerId));
    }

    @GetMapping("/state/{state}")
    @Operation(
            summary = "Get all the Complementary Studies  by state.",
            description = "Get all the Complementary Studies  by the complementary study state.",
            tags = {"Complementary Study"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ComplementaryStudy.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid state value",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<ComplementaryStudy>> getAllStudiesByState(@PathVariable EnumStudyState studyState) {
        List<ComplementaryStudy> studies = complementaryStudyService.getAllStudiesByState(studyState);
        return ResponseEntity.ok(studies);
    }
}