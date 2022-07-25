package com.yamakuprina.kotiki.ownermicroservice.service;

import com.yamakuprina.kotiki.ownermicroservice.repository.OwnerRepository;
import entities.Owner;
import entities.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(@Autowired OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public OwnerDto findById(String id) {
        return new OwnerDto(ownerRepository.findById(id).orElseThrow());
    }

    @Override
    public void save(OwnerDto ownerDto) throws Exception {
        ownerRepository.save(new Owner(ownerDto));
    }

    @Override
    public void delete(String id) throws Exception {
        ownerRepository.deleteById(id);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return ownersToOwnerDtos(ownerRepository.findAll());
    }

    private List<OwnerDto> ownersToOwnerDtos(List<Owner> owners) {
        ArrayList<OwnerDto> ownerDtos = new ArrayList<OwnerDto>();
        for (Owner owner : owners) {
            OwnerDto ownerDto = new OwnerDto(owner);
            ownerDtos.add(ownerDto);
        }
        return ownerDtos;
    }
}
