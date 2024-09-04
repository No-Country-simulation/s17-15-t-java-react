package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation.ConsultationDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.invoice.InvoiceDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ConsultationEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.InvoiceEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.ComplementaryStudy;
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

        for (DiagnosticEntity diagnostic : consultationEntity.getDiagnostics()) {

            // Acumula el costo de las cirugías del diagnóstico
            /*total = total.add(diagnostic.getSurgery().stream()
                    .map(Surgery::getCost)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));*/

            // Acumula el costo de los tratamientos del diagnóstico
            for (Treatment treatment : diagnostic.getTreatments()) {
                total = total.add(treatment.getTreatmentCost());

                // Acumula el costo de las hospitalizaciones del tratamiento
                /*for (Hospitalization hospitalization : treatment.getHospitalizations()) {
                    total = total.add(hospitalization.getCost());

                    // Acumula el costo de los estudios complementarios de la hospitalización
                    total = total.add(hospitalization.getComplementaryStudies().stream()
                            .map(ComplementaryStudy::getCost)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
                }*/
            }

            // Acumula el costo de los estudios complementarios del diagnóstico
           /* total = total.add(diagnostic.getComplementaryStudies().stream()
                    .map(ComplementaryStudy::getStudyCost)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));*/
        }

        // Acumula el costo de los estudios complementarios fuera de los diagnósticos
        /*total = total.add(consultationEntity.getComplementaryStudies().stream()
                .map(ComplementaryStudy::getStudyCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add));*/

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setConsultation(consultationEntity);
        invoiceEntity.setTotalCost(total);
        invoiceEntity.setInvoiceDate(LocalDate.now());

        InvoiceEntity savedInvoice = invoiceRepository.save(invoiceEntity);

        return invoiceMapper.toDto(savedInvoice);
    }


    public InvoiceDto getInvoiceById(Long id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada con ID: " + id));
        return invoiceMapper.toDto(invoiceEntity);
    }
}
