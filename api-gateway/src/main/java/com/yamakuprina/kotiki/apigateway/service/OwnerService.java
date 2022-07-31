package com.yamakuprina.kotiki.apigateway.service;

import entities.CatDto;
import entities.OwnerDto;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

public interface OwnerService {
    OwnerDto findById(String id) throws HttpStatusCodeException;

    void save(OwnerDto ownerDto) throws HttpStatusCodeException;

    void delete(String id) throws HttpStatusCodeException;

    List<OwnerDto> getAllOwners();

    List<CatDto> getCatsByOwnerId(String ownerId) throws HttpStatusCodeException;
}
