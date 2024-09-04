package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvoiceDto(
        LocalDate invoiceDate,
        BigDecimal totalCost
) {
}
