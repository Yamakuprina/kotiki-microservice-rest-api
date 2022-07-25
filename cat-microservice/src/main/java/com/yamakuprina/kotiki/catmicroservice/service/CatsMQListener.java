package com.yamakuprina.kotiki.catmicroservice.service;

import com.yamakuprina.kotiki.catmicroservice.config.MQConfig;
import entities.CatColor;
import entities.CatDto;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableRabbit
@Component
public class CatsMQListener {

    private final CatService catService;

    public CatsMQListener(@Autowired CatService catService) {
        this.catService = catService;
    }

    @RabbitListener(queues = MQConfig.CATS_QUEUE_ALL)
    public List<CatDto> getAllCats(String all) {
        return catService.getAllCats();
    }

    @RabbitListener(queues = MQConfig.CATS_QUEUE_ID)
    public CatDto findById(String id) {
        return catService.findById(id);
    }

    @RabbitListener(queues = MQConfig.CATS_QUEUE_SAVE)
    public String save(CatDto cat) throws Exception {
        try {
            catService.save(cat);
            return "OK";
        } catch (Exception e) {
            return "BAD_REQUEST";
        }
    }

    @RabbitListener(queues = MQConfig.CATS_QUEUE_DELETE)
    public String delete(String id) {
        try {
            catService.delete(id);
            return "OK";
        } catch (Exception e) {
            return "BAD_REQUEST";
        }
    }

    @RabbitListener(queues = MQConfig.CATS_QUEUE_COLOR)
    public List<CatDto> getCatsWithCatColor(CatColor color) {
        return catService.getCatsWithCatColor(color);
    }

    @RabbitListener(queues = MQConfig.CATS_QUEUE_FRIENDS)
    public List<CatDto> getFriendsById(String id) {
        return catService.getFriendsById(id);
    }

    @RabbitListener(queues = MQConfig.CATS_QUEUE_ADD_FRIEND)
    public String addCatToFriends(List<String> list) {
        try {
            catService.addCatToFriends(list.get(0), list.get(1));
            return "OK";
        } catch (Exception e) {
            return "BAD_REQUEST";
        }
    }

    @RabbitListener(queues = MQConfig.CATS_QUEUE_DELETE_FRIEND)
    public String deleteCatFromFriends(List<String> list) {
        try {
            catService.deleteCatFromFriends(list.get(0), list.get(1));
            return "OK";
        } catch (Exception e) {
            return "BAD_REQUEST";
        }
    }
}
