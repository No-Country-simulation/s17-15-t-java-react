package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hospitalization")
public class Hospitalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", length = 10)
    private LocalDate startDate;

    @Column(name = "end_date", length = 10)
    private LocalDate end_date;

    @Column(name = "cost", precision = 10, scale = 2 )
    private BigDecimal hospitalizationCost;

    @Column(name = "paid")
    private boolean paid = false;

    @OneToMany(mappedBy = "hospitalization", targetEntity = ComplementaryStudy.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComplementaryStudy> complementaryStudies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalization", fetch = FetchType.LAZY, targetEntity = Treatment.class)
    private List<Treatment> treatments =  new ArrayList<>();

}
