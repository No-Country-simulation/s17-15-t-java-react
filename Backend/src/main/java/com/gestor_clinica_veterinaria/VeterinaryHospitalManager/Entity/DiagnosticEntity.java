package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumGravedad;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diagnostic")
public class DiagnosticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_diagnostic")
    private LocalDate dateDiagnostic;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private EnumGravedad gravedad;

    @Column(name = "next_control_date")
    private LocalDate nextControlDate;

    //private Consulta consult;

    //private List<Tratamiento> tratamientos;
}
