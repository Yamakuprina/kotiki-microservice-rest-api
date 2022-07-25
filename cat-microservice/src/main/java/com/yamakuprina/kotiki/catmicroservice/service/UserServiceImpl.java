package com.yamakuprina.kotiki.catmicroservice.service;

import com.yamakuprina.kotiki.catmicroservice.repository.CatRepository;
import entities.Cat;
import entities.CatColor;
import entities.CatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final CatRepository catRepository;

    public UserServiceImpl(@Autowired CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public void addCatToFriends(String id, String friendId, String userOwnerId) throws Exception {
        Cat cat = catRepository.findById(id).orElseThrow();
        if (!Objects.equals(cat.getOwnerId(), userOwnerId)) throw new Exception();
        Cat friend = catRepository.findById(friendId).orElseThrow();
        cat.addFriend(friend);
        friend.addFriend(cat);
        catRepository.saveAll(List.of(cat, friend));
    }

    @Override
    public void deleteCatFromFriends(String id, String friendId, String userOwnerId) throws Exception {
        Cat cat = catRepository.findById(id).orElseThrow();
        if (!Objects.equals(cat.getOwnerId(), userOwnerId)) throw new Exception();
        Cat friend = catRepository.findById(friendId).orElseThrow();
        cat.deleteFriend(friend);
        friend.deleteFriend(cat);
        catRepository.saveAll(List.of(cat, friend));
    }

    @Override
    public List<CatDto> getAllCats(String userOwnerId) {
        List<Cat> allCats = catRepository.findByOwnerId(userOwnerId);
        return catsToCatDtos(allCats);
    }

    @Override
    public List<CatDto> getCatsWithCatColor(CatColor color, String userOwnerId) {
        List<Cat> allCats = catRepository.findByColorAndOwnerId(color, userOwnerId);
        return catsToCatDtos(allCats);
    }

    @Override
    public List<CatDto> getFriendsByCatId(String catId) {
        return catsToCatDtos(catRepository.findById(catId).orElseThrow().getFriends());
    }

    private List<CatDto> catsToCatDtos(List<Cat> cats) {
        ArrayList<CatDto> catDtos = new ArrayList<CatDto>();
        for (Cat cat : cats) {
            CatDto catDto = new CatDto(cat);
            catDto.setId(cat.getId());
            catDtos.add(catDto);
        }
        return catDtos;
    }
}
