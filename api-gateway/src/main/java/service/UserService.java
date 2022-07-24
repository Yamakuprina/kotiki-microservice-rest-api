package service;

import entities.CatColor;
import entities.CatDto;

import java.util.List;

public interface UserService {

    List<CatDto> getAllCats();

    List<CatDto> getCatsWithCatColor(CatColor color);

    void addCatToFriends(String id, String friendId);

    List<CatDto> getFriendsByCatId(String catId);

    void deleteCatFromFriends(String id, String friendId);
}
