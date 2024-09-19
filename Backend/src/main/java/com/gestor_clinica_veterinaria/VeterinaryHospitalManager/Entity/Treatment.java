package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatment")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "treatment_description", length = 500, nullable = false)
    private String treatmentDescription;

    @Column(name = "duration", length = 50, nullable = false)
    private String duration;

    @Column(name = "additional_observations", length = 200)
    private String additionalObservations;

    @Column(name = "treatment_cost", precision = 10, scale = 2 )
    private BigDecimal treatmentCost;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DiagnosticEntity.class)
    @JoinColumn(name = "diagnosis_id" )
    @JsonIgnore
    private DiagnosticEntity diagnosis;

    @ManyToOne(targetEntity = Hospitalization.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalization_id")
    @JsonIgnore
    private Hospitalization hospitalization;
}