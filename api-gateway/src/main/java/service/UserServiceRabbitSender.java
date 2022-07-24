package service;

import entities.CatColor;
import entities.CatDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceRabbitSender implements UserService {
    @Override
    public List<CatDto> getAllCats() {
        return null;
    }

    @Override
    public List<CatDto> getCatsWithCatColor(CatColor color) {
        return null;
    }

    @Override
    public void addCatToFriends(String id, String friendId) {

    }

    @Override
    public List<CatDto> getFriendsByCatId(String catId) {
        return null;
    }

    @Override
    public void deleteCatFromFriends(String id, String friendId) {

    }

//    @Autowired
//    private CatRepository catRepository;
//
//    @Autowired
//    private CatFriendsPairRepository catFriendsPairRepository;
//
//    @Override
//    public List<CatDto> getAllCats() {
//        Owner userOwner = ((KotikiUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOwner();
//        List<Cat> allCats = catRepository.findByOwner(userOwner);
//        return catsToCatDtos(allCats);
//    }
//
//    @Override
//    public List<CatDto> getCatsWithCatColor(CatColor color) {
//        Owner userOwner = ((KotikiUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOwner();
//        List<Cat> allCats = catRepository.findByColorAndOwner(color, userOwner);
//        return catsToCatDtos(allCats);
//    }
//
//    @Override
//    public void saveCatFriendsPair(CatFriendsPairDto catFriendsPairDto) {
//        CatFriendsPair catFriendsPair = new CatFriendsPair(catFriendsPairDto.getCat1Id(), catFriendsPairDto.getCat2Id());
//        if (catFriendsPairDto.getId() != null) {
//            catFriendsPair.setId(catFriendsPairDto.getId());
//        }
//        catFriendsPairRepository.save(catFriendsPair);
//    }
//
//    @Override
//    public List<CatDto> getFriendsByCatId(String catId) {
//        List<CatFriendsPair> catPairs = catFriendsPairRepository.findByCat1IdOrCat2Id(catId, catId);
//        List<String> catFriendsIds = catFriendsPairsToIds(catPairs, catId);
//        List<Cat> catFriends = catRepository.findAllById(catFriendsIds);
//        return catsToCatDtos(catFriends);
//    }
//
//    @Override
//    public void deleteCatFriendsPair(String catFriendsPairId) {
//        catFriendsPairRepository.deleteById(catFriendsPairId);
//    }
//
//    private List<CatDto> catsToCatDtos(List<Cat> cats) {
//        ArrayList<CatDto> catDtos = new ArrayList<CatDto>();
//        for (Cat cat : cats) {
//            CatDto catDto = new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
//            catDto.setId(cat.getId());
//            catDtos.add(catDto);
//        }
//        return catDtos;
//    }
//
//    private List<String> catFriendsPairsToIds(List<CatFriendsPair> pairs, String id){
//        List<String> ids = new ArrayList<>();
//        for (CatFriendsPair catFriendsPair : pairs) {
//            if (catFriendsPair.getCat1Id().equals(id)) {
//                ids.add(catFriendsPair.getCat2Id());
//            }
//            if (catFriendsPair.getCat2Id().equals(id)) {
//                ids.add(catFriendsPair.getCat1Id());
//            }
//        }
//        return ids;
//    }
}
