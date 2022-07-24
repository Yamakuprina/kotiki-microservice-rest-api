package service;


import entities.CatColor;
import entities.CatDto;
import entities.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import userDetails.KotikiUserDetails;

import java.util.List;

@Service
public class CatServiceRabbitSender implements CatService {

    @Autowired
    private OwnerService ownerService;

    @Override
    public List<CatDto> getAllCats() {
        return null;
    }

    @Override
    public CatDto findById(String id) {
        return null;
    }

    @Override
    public void save(CatDto cat) throws Exception {
        if (cat.getOwnerId() == null) cat.setOwnerId(((KotikiUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOwnerId());
        else if (ownerService.findById(cat.getOwnerId())==null) throw new Exception("No Owner found");
    }

//    @Override
//    public void setOwnerById(String catId, String ownerId) {
//
//    }

    @Override
    public void delete(String id) {

    }

//    @Override
//    public OwnerDto findOwnerByCatId(String id) {
//        return null;
//    }

    @Override
    public List<CatDto> getCatsWithCatColor(CatColor color) {
        return null;
    }

    @Override
    public List<CatDto> getFriendsById(String id) {
        return null;
    }

    @Override
    public void addCatToFriends(String id, String friendId) {

    }

    @Override
    public void deleteCatFromFriends(String id, String friendId) {

    }

//    @Autowired
//    private CatRepository catRepository;
//
//    @Autowired
//    private OwnerRepository ownerRepository;
//
//    @Override
//    public List<CatDto> getAllCats() {
//        return catsToCatDtos(catRepository.findAll());
//    }
//
//    @Override
//    public CatDto findById(String id) {
//        Cat cat = catRepository.findById(id).orElseThrow();
//        return new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
//    }
//
//    @Override
//    public void save(CatDto catDto) {
//        Cat cat = new Cat(catDto.getName(), catDto.getBirthDate(), catDto.getBreed(), catDto.getColor());
//        if (catDto.getOwner() != null) {
//            cat.setOwner(catDto.getOwner());
//        }
//        if (catDto.getId() != null) {
//            cat.setId(catDto.getId());
//        }
//        catRepository.save(cat);
//    }
//
//    @Override
//    public void setOwnerById(String catId, String ownerId) {
//        Cat cat = catRepository.findById(catId).orElseThrow();
//        Owner owner = ownerRepository.findById(ownerId).orElseThrow();
//        cat.setOwner(owner);
//        catRepository.save(cat);
//    }
//
//    @Override
//    public void delete(String id) {
//        catRepository.deleteById(id);
//    }
//
//    @Override
//    public OwnerDto findOwnerByCatId(String id) {
//        return new OwnerDto(catRepository.findById(id).orElseThrow().getOwner().getName(), catRepository.findById(id).orElseThrow().getOwner().getBirthDate());
//    }
//
//    @Override
//    public List<CatDto> getCatsWithCatColor(CatColor color) {
//        return catsToCatDtos(catRepository.findByColor(color));
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
}
