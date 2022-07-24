package com.yamakuprina.kotiki.catmicroservice.service;

import entities.Cat;
import entities.CatColor;
import entities.CatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yamakuprina.kotiki.catmicroservice.repository.CatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    public CatServiceImpl(@Autowired CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public List<CatDto> getFriendsById(String id) {
        return catsToCatDtos(catRepository.findById(id).orElseThrow().getFriends());
    }

    @Override
    public void addCatToFriends(String id, String friendId) {
        Cat cat = catRepository.findById(id).orElseThrow();
        Cat friend = catRepository.findById(id).orElseThrow();
        cat.addFriend(friend);
        catRepository.save(cat);
    }

    @Override
    public void deleteCatFromFriends(String id, String friendId) {
        Cat cat = catRepository.findById(id).orElseThrow();
        Cat friend = catRepository.findById(id).orElseThrow();
        cat.deleteFriend(friend);
        catRepository.save(cat);
    }

    @Override
    public List<CatDto> getAllCats() {
        return catsToCatDtos(catRepository.findAll());
    }

    @Override
    public CatDto findById(String id) {
        Cat cat = catRepository.findById(id).orElseThrow();
        return new CatDto(cat);
    }

    @Override
    public void save(CatDto catDto) {
        Cat cat = new Cat(catDto.getName(), catDto.getBirthDate(), catDto.getBreed(), catDto.getColor());
        if (catDto.getId() != null) {
            cat.setId(catDto.getId());
        }
        catRepository.save(cat);
    }

    @Override
    public void delete(String id) {
        catRepository.deleteById(id);
    }

    @Override
    public List<CatDto> getCatsWithCatColor(CatColor color) {
        return catsToCatDtos(catRepository.findByColor(color));
    }

    private List<CatDto> catsToCatDtos(List<Cat> cats) {
        ArrayList<CatDto> catDtos = new ArrayList<CatDto>();
        for (Cat cat : cats) {
            CatDto catDto = new CatDto(cat);
            catDtos.add(catDto);
        }
        return catDtos;
    }
}
