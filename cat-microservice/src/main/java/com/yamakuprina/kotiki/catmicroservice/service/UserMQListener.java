package com.yamakuprina.kotiki.catmicroservice.service;

import com.yamakuprina.kotiki.catmicroservice.config.MQConfig;
import entities.CatColor;
import entities.CatDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMQListener {
    private final UserService userService;

    public UserMQListener(@Autowired UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = MQConfig.USER_QUEUE_CATS_ALL)
    public List<CatDto> UserGetAllCats(String userOwnerId){
        return userService.getAllCats(userOwnerId);
    }

    @RabbitListener(queues = MQConfig.USER_QUEUE_CATS_COLOR)
    public List<CatDto> getCatsWithCatColor(CatColor color, String userOwnerId){
        return userService.getCatsWithCatColor(color, userOwnerId);
    }

    @RabbitListener(queues = MQConfig.USER_QUEUE_CATS_FRIENDS)
    public List<CatDto> getFriendsById(String id){
        return userService.getFriendsByCatId(id);
    }

    @RabbitListener(queues = MQConfig.USER_QUEUE_CATS_ADD_FRIEND)
    public String addCatToFriends(String id, String friendId, String userOwnerId){
        userService.addCatToFriends(id, friendId, userOwnerId);
        return "OK";
    }

    @RabbitListener(queues = MQConfig.USER_QUEUE_CATS_DELETE_FRIEND)
    public String deleteCatFromFriends(String id, String friendId, String userOwnerId){
        userService.deleteCatFromFriends(id, friendId, userOwnerId);
        return "OK";
    }
}
