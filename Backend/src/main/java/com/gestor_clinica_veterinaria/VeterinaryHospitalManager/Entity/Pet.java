package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumSexPet;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Especie;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Sexo;
import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String race;

  @Column(nullable = false)
  private Especie species = Especie.PERRO;

  @Column(nullable = false)
  private LocalDate birthdate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EnumSexPet sex ;

  @Column(nullable = false)
  private String allergies;

  @Column(nullable = false)
  private Boolean castrated = true;

  @Column(nullable = false)
  private Boolean active = true;

  private String details;

  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private Owner owner;

//  @OneToMany(mappedBy = "consultation")
//  private Lis<Consultation> consultationList;
}
