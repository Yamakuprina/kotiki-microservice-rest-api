package com.yamakuprina.kotiki.catmicroservice.service;

import com.yamakuprina.kotiki.catmicroservice.config.MQConfig;
import entities.CatColor;
import entities.CatDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatsMQListener {

    private final CatService catService;

    public CatsMQListener(@Autowired CatService catService) {
        this.catService = catService;
    }

    @RabbitListener(queues = MQConfig.QUEUE_ALL)
    public List<CatDto> getAllCats(){
        return catService.getAllCats();
    }

    @RabbitListener(queues = MQConfig.QUEUE_ID)
    public CatDto findById(String id){
        return catService.findById(id);
    }

    @RabbitListener(queues = MQConfig.QUEUE_SAVE)
    public void save(CatDto cat) throws Exception{
        catService.save(cat);
    }

    @RabbitListener(queues = MQConfig.QUEUE_DELETE)
    public void delete(String id){
        catService.delete(id);
    }

    @RabbitListener(queues = MQConfig.QUEUE_COLOR)
    public List<CatDto> getCatsWithCatColor(CatColor color){
        return catService.getCatsWithCatColor(color);
    }

    @RabbitListener(queues = MQConfig.QUEUE_FRIENDS)
    public List<CatDto> getFriendsById(String id){
        return catService.getFriendsById(id);
    }

    @RabbitListener(queues = MQConfig.QUEUE_ADD_FRIEND)
    public void addCatToFriends(String id, String friendId){
        catService.addCatToFriends(id, friendId);
    }

    @RabbitListener(queues = MQConfig.QUEUE_DELETE_FRIEND)
    public void deleteCatFromFriends(String id, String friendId){
        catService.deleteCatFromFriends(id, friendId);
    }
}
