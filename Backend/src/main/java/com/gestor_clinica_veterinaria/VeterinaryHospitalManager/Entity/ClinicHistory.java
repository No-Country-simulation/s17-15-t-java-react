package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clinic_history")

public class ClinicHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClinicHistory;


    @OneToMany(cascade = CascadeType.ALL)
    private List<ConsultationEntity> consultations;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DiagnosticEntity> diagnoses;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Treatment> treatments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Surgery> surgeries;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ComplementaryStudy> complementaryStudies;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Hospitalization> hospitalizations;
   // @ManyToOne
   // @JoinColumn(name = "pet_id")
   // private Pet pet;
   @OneToOne
   @JoinColumn(name = "pet_id", referencedColumnName = "id")
   private Pet pet;


}
