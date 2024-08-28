package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double surgeryCost;

    @ManyToOne
    @JoinColumn(name = "id_consultation")
    private DiagnosticEntity consultation;
}