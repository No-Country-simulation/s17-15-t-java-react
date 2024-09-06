package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.invoice.InvoiceDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.*;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.InvoiceMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ConsultationRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ConsultationRepository consultationRepository;
    private final InvoiceMapper invoiceMapper;


    @Transactional
    public InvoiceDto addInvoice(Long consultationId) {
        ConsultationEntity consultationEntity = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada con ID: " + consultationId));

        BigDecimal total = consultationEntity.getCostConsultation();

        // Acumular costos de los diagnósticos
        for (DiagnosticEntity diagnostic : consultationEntity.getDiagnostics()) {

            // Acumula el costo de las cirugías del diagnóstico
            total = total.add(diagnostic.getSurgerys().stream()
                    .map(Surgery::getSurgeryCost)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));

            // Acumula el costo de los tratamientos del diagnóstico
            for (Treatment treatment : diagnostic.getTreatments()) {
                total = total.add(treatment.getTreatmentCost());

                // Verifica si el tratamiento está asociado a una hospitalización
                if (treatment.getHospitalization() != null) {
                    Hospitalization hospitalization = treatment.getHospitalization();

                    // Acumula el costo base de la hospitalización
                    total = total.add(hospitalization.getHospitalizationCost());

                    // Acumula el costo de los tratamientos de la hospitalización
                    total = total.add(hospitalization.getTreatments().stream()
                            .map(Treatment::getTreatmentCost)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));

                    // Acumula el costo de los estudios complementarios de la hospitalización
                    total = total.add(hospitalization.getComplementaryStudies().stream()
                            .map(ComplementaryStudy::getStudyCost)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
                }
            }

            // Acumula el costo de los estudios complementarios del diagnóstico
            total = total.add(diagnostic.getComplementaryStudies().stream()
                    .map(ComplementaryStudy::getStudyCost)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        // Acumula el costo de los estudios complementarios fuera de los diagnósticos
        total = total.add(consultationEntity.getComplementaryStudies().stream()
                .map(ComplementaryStudy::getStudyCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setConsultation(consultationEntity);
        invoiceEntity.setTotalCost(total);
        invoiceEntity.setInvoiceDate(LocalDate.now());
        invoiceEntity.setVeterinarianName(consultationEntity.getVeterinarian().getUsername());
        invoiceEntity.setPetName(consultationEntity.getPet().getName());
        invoiceEntity.setOwnerName(consultationEntity.getPet().getOwner().getName());

        InvoiceEntity savedInvoice = invoiceRepository.save(invoiceEntity);

        return invoiceMapper.toDto(savedInvoice);
    }


    public InvoiceDto getInvoiceById(Long id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada con ID: " + id));
        return invoiceMapper.toDto(invoiceEntity);
    }
}
