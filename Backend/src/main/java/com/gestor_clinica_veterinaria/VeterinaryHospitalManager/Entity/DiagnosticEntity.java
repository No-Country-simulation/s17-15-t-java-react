package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumGravedad;
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

    @ManyToOne(targetEntity = ConsultationEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    private ConsultationEntity consultation;

    @OneToMany(mappedBy = "diagnosis", targetEntity = Treatment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Treatment> treatments;

    @OneToMany(mappedBy = "diagnosis", targetEntity = Surgery.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Surgery> surgerys;

    @OneToMany(mappedBy = "diagnosis", targetEntity = ComplementaryStudy.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ComplementaryStudy> complementaryStudies;
}
