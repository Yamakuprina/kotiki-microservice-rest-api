package com.yamakuprina.kotiki.apigateway.service;

import entities.CatColor;
import entities.CatDto;

import java.util.List;

public interface CatService {
    List<CatDto> getAllCats();

    CatDto findById(String id);

    void save(CatDto cat) throws Exception;

    void delete(String id) throws Exception;

    List<CatDto> getCatsWithCatColor(CatColor color);

    List<CatDto> getFriendsById(String id);

    void addCatToFriends(String id, String friendId) throws Exception;

    void deleteCatFromFriends(String id, String friendId) throws Exception;
}
