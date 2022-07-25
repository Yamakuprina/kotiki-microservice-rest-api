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
    public List<CatDto> getCatsWithCatColor(List<String> list){
        return userService.getCatsWithCatColor(CatColor.valueOf(list.get(0)), list.get(1));
    }

    @RabbitListener(queues = MQConfig.USER_QUEUE_CATS_FRIENDS)
    public List<CatDto> getFriendsById(String id){
        return userService.getFriendsByCatId(id);
    }

    @RabbitListener(queues = MQConfig.USER_QUEUE_CATS_ADD_FRIEND)
    public String addCatToFriends(List<String> list){
        try {
            userService.addCatToFriends(list.get(0), list.get(1), list.get(2));
            return "OK";
        } catch (Exception e){
            return "BAD_REQUEST";
        }
    }

    @RabbitListener(queues = MQConfig.USER_QUEUE_CATS_DELETE_FRIEND)
    public String deleteCatFromFriends(List<String> list){
        try {
            userService.deleteCatFromFriends(list.get(0), list.get(1), list.get(2));
            return "OK";
        } catch (Exception e){
            return "BAD_REQUEST";
        }
    }
}
