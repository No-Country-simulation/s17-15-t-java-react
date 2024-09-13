package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation.ConsultationDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.ConsultationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultation")
@RequiredArgsConstructor
@Validated
@Tag(name = "Consultation", description = "The Consultation API")
public class ConsultationController {

    private final ConsultationService consultationService;

    @PostMapping("/add")
    @Operation(
            summary = "Add a new Consultation",
            description = "Add a new Consultation",
            tags = {"Consultation"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Consultation object with fields: name, consultationDate, anamnesis, observatios, state = (PENDIENTE,\n" +
                            "    EN_PROCESO,\n" +
                            "    FINALIZADO,\n" +
                            "    CANCELADO)" + " and cost",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDto.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Consultation added successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDto.class))
                    ),

                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid Consultation data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    )

            }
    )
    public ResponseEntity<?> addConsultation(@Valid @RequestBody ConsultationDto consultationDto) {
        return ResponseEntity.ok(consultationService.addConsultation(consultationDto));
    }


    @PutMapping("/update/{consultationId}")
    @Operation(
            summary = "Update Consultation",
            description = "Partially or fully update a Consultation",
            tags = {"Consultation"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Consultation object with fields to update",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDto.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consultation Successfully updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDto.class))
                    )
            }
    )
    public ResponseEntity<?> updateConsultation(@PathVariable Long consultationId, @Valid @RequestBody ConsultationDto consultationDto) {
        return ResponseEntity.ok(consultationService.updateConsultation(consultationId, consultationDto));
    }


    @GetMapping("/{consultationId}")
    @Operation(
            summary = "Get Consultation by id",
            description = "Get Consultation by id",
            tags = {"Consultation"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consultation retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Consultation not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    )
            }
    )
    public ResponseEntity<?> getConsultationById(@PathVariable Long consultationId) {
        return ResponseEntity.ok(consultationService.getConsultationById(consultationId));
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all Consultations",
            description = "Get all Consultations",
            tags = {"Consultation"}
    )
    public ResponseEntity<Page<?>> getAllConsultations(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(consultationService.getAllConsultations(page, size));
    }


    @GetMapping("/search")
    @Operation(
            summary = "Search Consultations",
            description = "Search Consultations",
            tags = {"Consultation"}
    )
    public ResponseEntity<Page<?>> searchConsultations(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size,
                                                                     @RequestParam(defaultValue = "") String query) {
        return ResponseEntity.ok(consultationService.searchConsultations(page, size, query));
    }

    @GetMapping("/search/name-pet")
    @Operation(
            summary = "Search Consultations by name of Pet",
            description = "Search Consultations by name of Pet",
            tags = {"Consultation"}
    )
    public ResponseEntity<Page<?>> searchConsultationsByNameOfPet(@RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "10") int size,
                                                                            @RequestParam(defaultValue = "") String query) {
        return ResponseEntity.ok(consultationService.searchConsultationsByPetName(page, size, query));
    }


    @GetMapping("/search/name-owner")
    @Operation(
            summary = "Search Consultations by name of Owner",
            description = "Search Consultations by name of Owner",
            tags = {"Consultation"}
    )
    public ResponseEntity<Page<?>> searchConsultationsByOwnerName(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam(defaultValue = "") String query) {
        return ResponseEntity.ok(consultationService.searchConsultationsByOwnerName(page, size, query));
    }


    @GetMapping("/pet/{petId}")
    @Operation(
            summary = "Get Consultations by Pet",
            description = "Get Consultations by Pet",
            tags = {"Consultation"}
    )
    public ResponseEntity<Page<?>> getConsultationsByPetId(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size,
                                                                          @PathVariable Long petId) {
        return ResponseEntity.ok(consultationService.getConsultationsByPetId(page, size, petId));
    }

    @GetMapping("/veterinary/{veterinaryId}")
    @Operation(
            summary = "Get Consultations by Veterinary",
            description = "Get Consultations by Vet",
            tags = {"Consultation"}
    )
    public ResponseEntity<Page<?>> getConsultationsByVeterinaryId(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size,
                                                                          @PathVariable Long veterinaryId) {
        return ResponseEntity.ok(consultationService.getConsultationsByVeterinaryId(page, size, veterinaryId));
    }


    @GetMapping("/complementaryStudy/{complementaryStudyId}")
    @Operation(
            summary = "Get Consultation by Complementary Study",
            description = "Get Consultation by Complementary Study",
            tags = {"Consultation"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consultation retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Consultation not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    )
            }
    )
    public ResponseEntity<?> getConsultationByComplementaryStudyId(@PathVariable Long complementaryStudyId) {
        return ResponseEntity.ok(consultationService.getConsultationByComplementaryStudyId(complementaryStudyId));
    }


    @GetMapping("/diagnosis/{diagnosisId}")
    @Operation(
            summary = "Get Consultation by Diagnosis",
            description = "Get Consultation by Diagnosis",
            tags = {"Consultation"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consultation retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Consultation not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    )
            }
    )
    public ResponseEntity<?> getConsultationByDiagnosisId(@PathVariable Long diagnosisId) {
        return ResponseEntity.ok(consultationService.getConsultationByDiagnosisId(diagnosisId));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Consultation",
            description = "Delete Consultation",
            tags = {"Consultation"}
    )
    public ResponseEntity<?> deleteConsultation(@PathVariable Long id) {
        try{
            consultationService.deleteConsultation(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
