package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veterinarians")
public class Veterinarian extends UserEntity {

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String specialty;

    @Column(name = "professional_licence_number", nullable = false, unique = true)
    private String professionalLicenceNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Surgery> surgeries;
}
