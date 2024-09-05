package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.invoice.InvoiceDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invoice")
@Tag(name = "Invoice", description = "Invoice API")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/add/{consultationId}")
    @Operation(
            summary = "Add a new Invoice",
            description = "Add a new Invoice",
            tags = {"Invoice"},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Invoice added successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvoiceDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid Invoice data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    )
            }
    )
    public ResponseEntity<InvoiceDto> addInvoice(@PathVariable Long consultationId) {

        return ResponseEntity.ok(invoiceService.addInvoice(consultationId));

    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Invoice by id",
            description = "Get Invoice by id",
            tags = {"Invoice"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Invoice found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvoiceDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invoice not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    )
            }
    )
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }
}
