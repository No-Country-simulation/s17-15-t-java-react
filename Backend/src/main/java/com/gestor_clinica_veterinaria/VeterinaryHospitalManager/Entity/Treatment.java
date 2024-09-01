package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

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
    private  Long id_treatment;

    @Column(name = "treatment_description", length = 500, nullable = false)
    private String treatmentDescription;

    @Column(name = "duration", length = 50, nullable = false)
    private String duration;

    @Column(name = "aditional_observations", length = 200)
    private String aditionalObservations;

    @Column(name = "treatment_cost", precision = 10, scale = 2 )
    private BigDecimal treatmentCost;

    @ManyToOne
    @JoinColumn(name = "id" )
    private DiagnosticEntity diagnosis;

//    @Column(name = "hospitalization")
//    private List<Hospitalization> hospitalizations;

}