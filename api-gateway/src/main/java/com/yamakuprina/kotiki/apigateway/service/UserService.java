package com.yamakuprina.kotiki.apigateway.service;

import entities.CatColor;
import entities.CatDto;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

public interface UserService {

    List<CatDto> getAllCats(String userOwnerId);

    List<CatDto> getCatsWithCatColor(CatColor color, String userOwnerId);

    void addCatToFriends(String id, String friendId, String userOwnerId) throws HttpStatusCodeException;

    List<CatDto> getFriendsByCatId(String catId) throws HttpStatusCodeException;

    void deleteCatFromFriends(String id, String friendId, String userOwnerId) throws HttpStatusCodeException;
}
