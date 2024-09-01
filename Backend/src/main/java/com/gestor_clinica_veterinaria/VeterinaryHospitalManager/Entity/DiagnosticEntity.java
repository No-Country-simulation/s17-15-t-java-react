package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumGravedad;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.ComplementaryStudy;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diagnosis")
public class DiagnosticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(name = "diagnosis_date")
    private LocalDate diagnosisDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private EnumGravedad severidad;

    @Column(name = "next_check_up")
    private LocalDate nextCheckUp;

    /*@ManyToOne(targetEntity = Consulta.class)
    @JoinColumn(name = "consulta_id")
    private Consultation consultation;*/

    @OneToMany(targetEntity = Treatment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_diagnosis")
    private List<Treatment> treatments;

    /*@OneToMany(targetEntity = Surgery.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_diagnosis")
    private List<Surgery> surgerys;*/

    @OneToMany(targetEntity = ComplementaryStudy.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_diagnosis")
    private List<ComplementaryStudy> complementaryStudies;
}
