package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumState;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consultation")
public class ConsultationEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "consultation_date", nullable = false)
    private LocalDate consultationDate;
    private String anamnesis;
    private String observations;

    @Enumerated(EnumType.STRING)
    private EnumState state;
    @Column(name = "cost_consultation")
    private BigDecimal costConsultation;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Veterinarian.class)
    @JoinColumn(name = "veterinarian_id")
    private Veterinarian veterinarian;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pet.class)
    @JoinColumn(name = "pet_id")
    private Pet pet;


    @OneToMany(mappedBy = "consultation", targetEntity = DiagnosticEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiagnosticEntity> diagnostics;


    @OneToMany(mappedBy = "consultation", targetEntity = ComplementaryStudy.class, fetch = FetchType.LAZY)
    private List<ComplementaryStudy> complementaryStudies = new ArrayList<>();

    @OneToOne(mappedBy = "consultation")
    private InvoiceEntity invoice;


}
