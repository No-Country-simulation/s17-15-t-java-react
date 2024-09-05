package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "veterinarians")
public class Veterinarian {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false, unique = true)
    private String professionalLicenceNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Surgery> surgeries;

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinarian")
    private List<Consultation> consultation;*/

}
