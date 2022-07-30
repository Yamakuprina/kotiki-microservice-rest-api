package com.yamakuprina.kotiki.apigateway.service;

import entities.CatColor;
import entities.CatDto;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

public interface CatService {
    List<CatDto> getAllCats();

    CatDto findById(String id) throws HttpStatusCodeException;

    void save(CatDto cat) throws HttpStatusCodeException;

    void delete(String id) throws HttpStatusCodeException;

    List<CatDto> getCatsWithCatColor(CatColor color);

    List<CatDto> getFriendsById(String id) throws HttpStatusCodeException;

    void addCatToFriends(String id, String friendId) throws HttpStatusCodeException;

    void deleteCatFromFriends(String id, String friendId) throws HttpStatusCodeException;
}
