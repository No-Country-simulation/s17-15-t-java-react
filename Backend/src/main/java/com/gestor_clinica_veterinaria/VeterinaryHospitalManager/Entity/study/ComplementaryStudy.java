package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.tools.Diagnostic;
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
    private Long id_complementaryStudy;

    @Column(name = "examination_day", length = 10, nullable = false)
    private LocalDate examinationDate;

    @Column(name = "study_type", length = 50)
    private String studyType;

    @Column(name = "study_result", length = 300)
    private String studyResult;

    @Column(name = "study_state" )
    @Enumerated(EnumType.STRING)
    private EnumStudyState studyState;

    @Lob
    @Column(name = "file")
    private byte[] studyFile;

    @Column(name = "study_cost")
    private Double studyCost ;

    @ManyToOne
    @JoinColumn(name = "id")
    private DiagnosticEntity diagnosis ;

//    @ManyToOne
//    @JoinColumn(name = "consultation_id")
//    private Consultation consultation;

//    @ManyToOne
//    @JoinColumn(name = "hospitalization_id")
//    private Hospitalization hospitalization;
}
