package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplementaryStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_complementaryStudy;

    @Column(name = "examination_day", length = 10, nullable = false)
    private LocalDate examinationDate;

    @Column(name = "study_type")
    private EnumStudyType studyType;

    @Column(name = "study_result", length = 300)
    private String studyResult;

    @Column(name = "study_state" )
    private EnumStudyState studyState;

    @Column(name = "file")
    private byte[] studyFile;

    @Column(name = "study_cost")
    private Double studyCost ;

    @Column(name = "id_diagnosis" )
    private Long idDianosis ;

    @Column(name = "id_consultation")
    private Long idConsultation;
}
