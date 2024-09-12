package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.context.service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyCreatedResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ConsultationEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.ComplementaryStudyMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.ConsultationMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ComplementaryStudyRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ConsultationRepository;
//import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
//import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.HospitalizationRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.ComplementaryStudyService;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ComplementaryStudyServiceTests {

    @Mock
    private ComplementaryStudyRepository complementaryStudyRepository;

    @Mock
    private ComplementaryStudyMapper complementaryStudyMapper;

//    @Mock
//    private HospitalizationRepository hospitalizationRepository;
//
//    @Mock
//    private DiagnosticRepository diagnosisRepository;
//
    @Mock
    private ConsultationRepository consultationRepository;
    @Mock
    private ConsultationMapper consultationMapper;

    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private ComplementaryStudyService complementaryStudyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddComplementaryStudy_Success_withOutFile() {
        // Arrange
        LocalDate examinationDate = LocalDate.of(2024, 9, 9);
        EnumStudyState studyState = EnumStudyState.REVISADOS;
        BigDecimal studyCost = BigDecimal.valueOf(500.50);
        Optional<Long> consultationId = Optional.of(50L);
        StudyRequest studyRequest = new StudyRequest(examinationDate, "plaquetas", "plaquetas por debajo de lo normal 300/500", studyState, null, studyCost,consultationId, null, null);

        // Mock de la consulta
        Long idConsultation = 50L;
        ConsultationEntity mockConsultation = new ConsultationEntity();
        mockConsultation.setId(idConsultation);
        mockConsultation.setComplementaryStudies(new ArrayList<>());
        //MultipartFile file = mock(MultipartFile.class);

        ComplementaryStudy study = new ComplementaryStudy();
        when(consultationRepository.findById(idConsultation)).thenReturn(Optional.of(mockConsultation));
        when(complementaryStudyMapper.toEntity(studyRequest)).thenReturn(study);
        //when(fileStorageService.saveFile(file)).thenReturn("fileUrl");

        ComplementaryStudy savedStudy = new ComplementaryStudy();
        savedStudy.setId(1L);
        when(complementaryStudyRepository.save(study)).thenReturn(savedStudy);

        // Act
        StudyCreatedResponse response = complementaryStudyService.addComplementaryStudy(studyRequest, null);

        // Assert
        assertEquals("¡El estudio complementario se registró exitosamente!", response.message());
        assertNotNull(response.studyId());

    }

    @Test
    public void testAddComplementaryStudy_Success_withFile() {
        // Arrange
        LocalDate examinationDate = LocalDate.of(2024, 9, 9);
        EnumStudyState studyState = EnumStudyState.REVISADOS;
        BigDecimal studyCost = BigDecimal.valueOf(500.50);
        Optional<Long> consultationId = Optional.of(50L);
        StudyRequest studyRequest = new StudyRequest(examinationDate, "plaquetas", "plaquetas por debajo de lo normal 300/500", studyState, null, studyCost,consultationId, null, null);

        // Mock de la consulta
        Long idConsultation = 50L;
        ConsultationEntity mockConsultation = new ConsultationEntity();
        mockConsultation.setId(idConsultation);
        mockConsultation.setComplementaryStudies(new ArrayList<>());
        MultipartFile file = mock(MultipartFile.class);

        ComplementaryStudy study = new ComplementaryStudy();
        when(consultationRepository.findById(idConsultation)).thenReturn(Optional.of(mockConsultation));
        when(complementaryStudyMapper.toEntity(studyRequest)).thenReturn(study);
        when(fileStorageService.saveFile(file)).thenReturn("fileUrl");

        ComplementaryStudy savedStudy = new ComplementaryStudy();
        savedStudy.setId(1L);
        when(complementaryStudyRepository.save(study)).thenReturn(savedStudy);

        // Act
        StudyCreatedResponse response = complementaryStudyService.addComplementaryStudy(studyRequest, file);

        // Assert
        assertEquals("¡El estudio complementario se registró exitosamente!", response.message());
        assertNotNull(response.studyId());

    }
//    @Test
//    public void testAddComplementaryStudy_HospitalizationNotFound() {
//        // Arrange
//        StudyRequest studyRequest = new StudyRequest(/* parámetros necesarios */);
//        MultipartFile file = mock(MultipartFile.class);
//
//        when(complementaryStudyMapper.toEntity(studyRequest)).thenReturn(new ComplementaryStudy());
//        when(fileStorageService.saveFile(file)).thenReturn("fileUrl");
//        when(hospitalizationRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Act and Assert
//        assertThrows(EntityNotFoundException.class, () -> complementaryStudyService.addComplementaryStudy(studyRequest, file));
//    }
//
//    @Test
//    public void testAddComplementaryStudy_DataAccessException() {
//        // Arrange
//        StudyRequest studyRequest = new StudyRequest(/* parámetros necesarios */);
//        MultipartFile file = mock(MultipartFile.class);
//
//        when(complementaryStudyMapper.toEntity(studyRequest)).thenReturn(new ComplementaryStudy());
//        when(fileStorageService.saveFile(file)).thenReturn("fileUrl");
//        when(complementaryStudyRepository.save(any())).thenThrow(new RuntimeException("Database error"));
//
//        // Act and Assert
//        StudyCreatedResponse response = complementaryStudyService.addComplementaryStudy(studyRequest, file);
//        assertEquals("Error: Database error", response.getMessage());
//    }


}
