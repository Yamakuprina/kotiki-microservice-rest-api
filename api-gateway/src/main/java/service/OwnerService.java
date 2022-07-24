package service;

import entities.CatDto;
import entities.OwnerDto;


import java.util.List;

public interface OwnerService {
    OwnerDto findById(String id);

    void save(OwnerDto ownerDto);

    void delete(String id);

    List<OwnerDto> getAllOwners();

    List<CatDto> getCatsByOwnerId(String ownerId);
}
