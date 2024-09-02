package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Owner;
import java.util.List;

public class OwnerMapper {

  public static OwnerResponse toDto(Owner owner) {
    if (owner == null) {
      return null;
    }

    return new OwnerResponse(
        owner.getId(),
        owner.getName(),
        owner.getLastname(),
        owner.getPhone(),
        owner.getEmail(),
        owner.getAddress(),
        PetMapper.toListDto(owner.getPets()));
  }

  public static List<OwnerResponse> toListDto(List<Owner> ownerList) {
    if (ownerList == null) {
      return null;
    }

    return ownerList
        .stream()
        .map(OwnerMapper::toDto)
        .toList();
  }

  public static Owner toEntity(OwnerRequest ownerRequest) {
    if (ownerRequest == null) {
      return null;
    }

    Owner owner = new Owner();
    owner.setName(ownerRequest.name());
    owner.setLastname(ownerRequest.lastname());
    owner.setPhone(ownerRequest.phone());
    owner.setEmail(ownerRequest.email());
    owner.setAddress(ownerRequest.address());

    return owner;
  }

  public static void updateEntity(Owner oldOwner, OwnerRequest owner) {
    if (oldOwner == null || owner == null) {
      return;
    }

    if (owner.name() != null) {
      oldOwner.setName(owner.name());
    }
    if (owner.lastname() != null) {
      oldOwner.setLastname(owner.lastname());
    }
    if (owner.phone() != null) {
      oldOwner.setPhone(owner.phone());
    }
    if (owner.email() != null) {
      oldOwner.setEmail(owner.email());
    }
    if (owner.address() != null) {
      oldOwner.setAddress(owner.address());
    }
  }
}
