package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "surgery")
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateSurgery;
    private String surgeryType;
    private String surgeryProcedure;
    private String observations;
    private String postSurgeryRecommendations;
    private BigDecimal surgeryCost;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinarian_id")
    private Veterinarian veterinarian;


    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id")
    private  ConsultationEntity consultationEntity;*/


    @ManyToOne(targetEntity = DiagnosticEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnostic_id")
    private DiagnosticEntity diagnosis;

}