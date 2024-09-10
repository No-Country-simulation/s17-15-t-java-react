package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "complementary_study")
public class ComplementaryStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "examination_day", length = 10, nullable = false)
    private LocalDate examinationDate;

    @Column(name = "study_type", length = 50, nullable = false)
    private String studyType;

    @Column(name = "study_result", length = 300)
    private String studyResult;

    @Column(name = "study_state" , nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumStudyState studyState;

    @Column(name = "file")
    private String studyFile;

    @Column(name = "study_cost", precision = 10, scale = 2, nullable = false )
    private BigDecimal studyCost ;

    @ManyToOne(targetEntity = DiagnosticEntity.class)
    @JoinColumn(name = "diagnosis_id")
    private DiagnosticEntity diagnosis;

    @ManyToOne(targetEntity = ConsultationEntity.class)
    @JoinColumn(name = "consultation_id")
    private ConsultationEntity consultation;

    @ManyToOne
    @JoinColumn(name = "hospitalization_id")
    private Hospitalization hospitalization;
}