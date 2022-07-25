package com.yamakuprina.kotiki.ownermicroservice.service;

import entities.OwnerDto;

import java.util.List;

public interface OwnerService {
    OwnerDto findById(String id);

    void save(OwnerDto ownerDto) throws Exception;

    void delete(String id) throws Exception;

    List<OwnerDto> getAllOwners();

}
