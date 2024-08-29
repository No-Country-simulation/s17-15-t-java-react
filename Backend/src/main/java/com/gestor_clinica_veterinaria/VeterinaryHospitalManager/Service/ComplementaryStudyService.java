package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.ComplementaryStudyDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.EnumStudyState;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.ComplementaryStudyMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ComplementaryStudyRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.ComplementaryStudyNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.TreatmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComplementaryStudyService {

    private final ComplementaryStudyRepository complementaryStudyRepository;
    private final ComplementaryStudyMapper complementaryStudyMapper;

    public ComplementaryStudyDto addComplementaryStudy(ComplementaryStudyDto dto){
        ComplementaryStudy study = complementaryStudyMapper.toEntity(dto);
        study = complementaryStudyRepository.save(study);
        return complementaryStudyMapper.toDto(study);
    }

    public List<ComplementaryStudyDto> getAllComplementaryStudies(){
        List<ComplementaryStudy> studyList = complementaryStudyRepository.findAll();
        return  complementaryStudyMapper.toDtoList(studyList);
    }
    public ComplementaryStudyDto getStudyById(Long studyId){
        ComplementaryStudy study = complementaryStudyRepository.findById(studyId).orElseThrow(() -> new ComplementaryStudyNotFoundException("El id del estudio complementario ingresao es incorrecto o no existe"));
        return complementaryStudyMapper.toDto(study);
    }

    public List<ComplementaryStudyDto> getAllStudiesByPetId(Long petId){
        List<ComplementaryStudy> treatmentList = complementaryStudyRepository.findAllById(Collections.singleton(petId));
        return  complementaryStudyMapper.toDtoList(treatmentList);
    }
    public List<ComplementaryStudyDto> getAllStudiesByOwnerId(Long ownerId){
        List<ComplementaryStudy> treatmentList = complementaryStudyRepository.findAllById(Collections.singleton(ownerId));
        return  complementaryStudyMapper.toDtoList(treatmentList);
    }
    public List<ComplementaryStudyDto> getAllStudiesByState(EnumStudyState state) {
        return complementaryStudyMapper.toDtoList(complementaryStudyRepository.findByStudyState(state));
    }
    public ComplementaryStudyDto updateStudy(Long studyId, ComplementaryStudyDto dto){

        Optional<ComplementaryStudy> studyOptional = complementaryStudyRepository.findById(studyId);

        if (studyOptional.isPresent()) {

            ComplementaryStudy existingStudy = studyOptional.get();

            if (dto.examinationDate() != null){
                existingStudy.setExaminationDate(dto.examinationDate());
            }
            if (dto.studyCost() != null){
                existingStudy.setStudyCost(dto.studyCost());
            }
            if (dto.studyState() != null){
                existingStudy.setStudyState(dto.studyState());
            }
            if (dto.studyResult() != null){
                existingStudy.setStudyResult(dto.studyResult());
            }
            if (dto.studyFile() != null){
                existingStudy.setStudyFile(dto.studyFile());
            }
//            if (dto.consultation() != null){
//                existingStudy.setConsultation(dto.consultation());
//            }
            if (dto.dianosis() != null){
                existingStudy.setDiagnosis(dto.dianosis());
            }
            if (dto.studyType() != null){
                existingStudy.setStudyType(dto.studyType());
            }

            //if (dto.hospitalization() != null){
//                existingStudy.setHospitalization(dto.hospitalization());
//            }

            ComplementaryStudy updatedStudy =  complementaryStudyRepository.save(existingStudy);
            return complementaryStudyMapper.toDto(updatedStudy);

        } else{
            throw  new  TreatmentNotFoundException("No se ha podido actualizar el tratamiento porque el id ingresado es incorrecto o no existe: " + studyId);
        }
    }

    public void deleteStudyById(Long studyId){
        if (!complementaryStudyRepository.existsById(studyId)){
            throw new TreatmentNotFoundException("El tratamiento que que pretende eliminar no existe o es incorrecto veritfique el id ingresado: " + studyId);
        }else {
            complementaryStudyRepository.deleteById(studyId);
        }
    }
}
