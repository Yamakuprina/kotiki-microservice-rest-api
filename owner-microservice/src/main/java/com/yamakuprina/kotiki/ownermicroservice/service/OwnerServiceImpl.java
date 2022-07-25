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

//    @Override
//    public OwnerDto findById(String id) {
//        return new OwnerDto(ownerRepository.findById(id).orElseThrow().getName(), ownerRepository.findById(id).orElseThrow().getBirthDate());
//    }
//
//    @Override
//    public void save(OwnerDto ownerDto) {
//        Owner owner = new Owner(ownerDto.getName(), ownerDto.getBirthDate());
//        if (ownerDto.getCats() != null) {
//            owner.setCats(ownerDto.getCats());
//        }
//        if (ownerDto.getId() != null) {
//            owner.setId(ownerDto.getId());
//        }
//        ownerRepository.save(owner);
//    }
//
//    @Override
//    public void delete(String id) {
//        ownerRepository.deleteById(id);
//    }
//
//    @Override
//    public List<OwnerDto> getAllOwners() {
//        return ownersToOwnerDtos(ownerRepository.findAll());
//    }
//
//    @Override
//    public List<CatDto> getCatsByOwnerId(String ownerId) {
//        List<Cat> allCats = catRepository.findAll();
//        List<Cat> ownerCats = new ArrayList<>();
//        for (Cat cat : allCats) {
//            if (cat.getOwner().getId().equals(ownerId)) {
//                ownerCats.add(cat);
//            }
//        }
//        return catsToCatDtos(ownerCats);
//    }
//
//    private List<CatDto> catsToCatDtos(List<Cat> cats) {
//        ArrayList<CatDto> catDtos = new ArrayList<CatDto>();
//        for (Cat cat : cats) {
//            CatDto catDto = new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
//            catDtos.add(catDto);
//        }
//        return catDtos;
//    }
//
    private List<OwnerDto> ownersToOwnerDtos(List<Owner> owners) {
        ArrayList<OwnerDto> ownerDtos = new ArrayList<OwnerDto>();
        for (Owner owner : owners) {
            OwnerDto ownerDto = new OwnerDto(owner);
            ownerDtos.add(ownerDto);
        }
        return ownerDtos;
    }
}
