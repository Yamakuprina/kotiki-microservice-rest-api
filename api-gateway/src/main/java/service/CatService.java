package service;

import entities.CatColor;
import entities.CatDto;
import entities.OwnerDto;

import java.util.List;

public interface CatService {
    List<CatDto> getAllCats();

    CatDto findById(String id);

    void save(CatDto cat) throws Exception;

    //void setOwnerById(String catId, String ownerId);

    void delete(String id);

    //OwnerDto findOwnerByCatId(String id);

    List<CatDto> getCatsWithCatColor(CatColor color);

    List<CatDto> getFriendsById(String id);

    void addCatToFriends(String id, String friendId);

    void deleteCatFromFriends(String id, String friendId);
}
