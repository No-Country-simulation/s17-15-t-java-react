package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id_treatment;

    @Column(name = "start_Date", length = 10, nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", length = 10, nullable = false)
    private LocalDate endDate;

    @Column(name = "treatment_description", length = 500, nullable = false)
    private String treatmentDescription;

    @Column(name = "duration", length = 50, nullable = false)
    private String duration;

    @Column(name = "aditional_observations", length = 200)
    private String aditionalObservations;

    @Column(name = "treatment_cost", precision = 10, scale = 2, nullable = false )
    private BigDecimal treatmentCost;

    @Column(name = "id_diagnosis", nullable = false)
    private Long idDiagnosis;


}
