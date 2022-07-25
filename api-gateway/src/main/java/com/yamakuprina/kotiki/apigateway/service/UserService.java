package com.yamakuprina.kotiki.apigateway.service;

import entities.CatColor;
import entities.CatDto;

import java.util.List;

public interface UserService {

    List<CatDto> getAllCats(String userOwnerId);

    List<CatDto> getCatsWithCatColor(CatColor color, String userOwnerId);

    void addCatToFriends(String id, String friendId, String userOwnerId) throws Exception;

    List<CatDto> getFriendsByCatId(String catId);

    void deleteCatFromFriends(String id, String friendId, String userOwnerId) throws Exception;
}
