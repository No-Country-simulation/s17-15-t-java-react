package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.impl;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Owner;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions.ResourceDuplicatedException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions.ResourceNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.OwnerMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.OwnerRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.OwnerService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

  private final OwnerRepository ownerRepository;

  @Autowired
  public OwnerServiceImpl(OwnerRepository ownerRepository) {
    this.ownerRepository = ownerRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<OwnerResponse> findAllOwner() {
    return OwnerMapper.toListDto(ownerRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<OwnerResponse> finAllOwnerByName(String name) {
    List<Owner> ownerList = ownerRepository.findAllByNameContainingIgnoreCase(name);
    return OwnerMapper.toListDto(ownerList);
  }

  @Transactional(readOnly = true)
  @Override
  public List<OwnerResponse> findAllOwnerByLastname(String lastname) {
    List<Owner> ownerList = ownerRepository.findAllByLastnameContainingIgnoreCase(lastname);
    return OwnerMapper.toListDto(ownerList);
  }

  @Transactional(readOnly = true)
  @Override
  public OwnerResponse OwnerById(Long id) {
    Owner ownerFound = ownerRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("El owner con el id " + id + " no existe"));
    return OwnerMapper.toDto(ownerFound);
  }

  @Override
  public OwnerResponse saveOwner(OwnerRequest ownerRequest) {
    boolean ownerExistsByEmail = ownerRepository.existsByEmail(ownerRequest.email());

    if (ownerExistsByEmail) {
      throw new ResourceDuplicatedException("El email ya esta en uso");
    }

    Owner ownerEntity = OwnerMapper.toEntity(ownerRequest);
    Owner ownerCreated = ownerRepository.save(ownerEntity);
    return OwnerMapper.toDto(ownerCreated);
  }

  @Override
  public OwnerResponse updateOwner(Long id, OwnerRequest ownerRequest) {
    Owner ownerExists = ownerRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("El owner con el id " + id + " no existe"));
    boolean ownerExistByEmail = ownerRepository.existsByEmail(ownerRequest.email());

    if (ownerExistByEmail && !Objects.equals(ownerExists.getEmail(), ownerRequest.email())) {
      throw new ResourceDuplicatedException("El email ya esta en uso");
    }

    OwnerMapper.updateEntity(ownerExists, ownerRequest);
    Owner ownerUpdated = ownerRepository.save(ownerExists);
    return OwnerMapper.toDto(ownerUpdated);
  }

  @Override
  public void deleteOwner(Long id) {
    Owner ownerExists = ownerRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("El owner con el id " + id + " no existe"));

    ownerRepository.delete(ownerExists);
  }
}
