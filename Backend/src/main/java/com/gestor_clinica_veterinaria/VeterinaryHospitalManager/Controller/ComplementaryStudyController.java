package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyCreatedResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.ComplementaryStudyService;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/complementaryStudies")
@RequiredArgsConstructor
@Tag(name="Complementary Study", description = "Endpoints to manage Complementary Studies")
public class ComplementaryStudyController {
    private final ComplementaryStudyService complementaryStudyService;
    private final FileStorageService fileStorageService;

    @PostMapping(value = "/add")
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
    public ResponseEntity<StudyCreatedResponse> addStudy(@RequestBody StudyRequest studyRequest) {
        try {
            StudyCreatedResponse response = complementaryStudyService.addComplementaryStudy(studyRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new StudyCreatedResponse("Error: " + e.getMessage() + " // request: " + studyRequest, null));
        }
    }

    @PutMapping(value = "/update/{studyId}")
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
                                    schema = @Schema(implementation = StudyResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<StudyResponse> updateComplementaryStudy(@PathVariable Long studyId,  @RequestPart StudyRequest dto) {
        return ResponseEntity.ok(complementaryStudyService.updateStudy(studyId, dto));
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
                                    schema = @Schema(implementation = StudyResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<StudyResponse>> getAllTreatments() {
        return ResponseEntity.ok(complementaryStudyService.getAllComplementaryStudies());
    }

    @GetMapping("/{studyId}")
    @Operation(
            summary = "Get Complementary Study by id",
            description = "Get Complementary Study by id",
            tags = {"Complementary Study"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudyResponse.class)
                            )
                    )
            }
    )
//    public ResponseEntity<StudyResponse> getComplementaryStudyById(@PathVariable Long studyId) {
//        return ResponseEntity.ok(complementaryStudyService.getStudyById(studyId));
//    }
    public ResponseEntity<StudyResponse> getStudyById(@PathVariable Long studyId) {
        try {
            StudyResponse response = complementaryStudyService.getStudyById(studyId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
                                    schema = @Schema(implementation = StudyResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid pet id value",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<StudyResponse>> getAllStudiesByPetId(@PathVariable Long petId) {
        try {
            List<StudyResponse> studyResponses = complementaryStudyService.getAllStudiesByPetId(petId);
            return new ResponseEntity<>(studyResponses, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  //  public ResponseEntity<List<StudyResponse>> getAllStudiesByPetId(@PathVariable Long petId) {
  //      List<StudyResponse> studies = complementaryStudyService.getAllStudiesByPetId(petId);
  //      return ResponseEntity.ok(studies);
  //  }

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
                                    schema = @Schema(implementation = StudyResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid owner id value",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<StudyResponse>> getStudiesByOwnerId(@PathVariable Long ownerId) {
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
                                    schema = @Schema(implementation = StudyResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid state value",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<StudyResponse>> getAllStudiesByState(@PathVariable EnumStudyState studyState) {
        List<StudyResponse> studies = complementaryStudyService.getAllStudiesByState(studyState);
        return ResponseEntity.ok(studies);
    }

}