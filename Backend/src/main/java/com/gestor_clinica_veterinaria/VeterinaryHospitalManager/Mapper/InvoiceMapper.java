package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.invoice.InvoiceDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ConsultationEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.InvoiceEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    public InvoiceEntity toEntity(InvoiceDto invoiceDto) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setInvoiceDate(invoiceDto.invoiceDate());
        return invoiceEntity;
    }

    public InvoiceDto toDto(InvoiceEntity invoiceEntity) {
        return new InvoiceDto(invoiceEntity.getInvoiceDate(),
                invoiceEntity.getTotalCost(),
                invoiceEntity.getVeterinarianName(),
                invoiceEntity.getOwnerName(),
                invoiceEntity.getPetName());
    }
}
