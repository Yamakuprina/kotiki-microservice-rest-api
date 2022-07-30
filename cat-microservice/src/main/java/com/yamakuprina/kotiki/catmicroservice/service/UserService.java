package com.yamakuprina.kotiki.catmicroservice.service;

import entities.CatColor;
import entities.CatDto;

import java.util.List;

public interface UserService {

    CatDto findById(String id);

    List<CatDto> getAllCats(String userOwnerId);

    List<CatDto> getCatsWithCatColor(CatColor color, String userOwnerId);

    void addCatToFriends(String id, String friendId, String userOwnerId) throws Exception;

    List<CatDto> getFriendsByCatId(String catId);

    void deleteCatFromFriends(String id, String friendId, String userOwnerId) throws Exception;
}
