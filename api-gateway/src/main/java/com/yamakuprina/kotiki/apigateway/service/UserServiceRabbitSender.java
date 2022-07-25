package com.yamakuprina.kotiki.apigateway.service;

import com.yamakuprina.kotiki.apigateway.config.Queues;
import entities.CatColor;
import entities.CatDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceRabbitSender implements UserService {

    private final AmqpTemplate template;

    public UserServiceRabbitSender(@Autowired AmqpTemplate template) {
        this.template = template;
    }

    @Override
    public List<CatDto> getAllCats(String userOwnerId) {
        return template.convertSendAndReceiveAsType(
                Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_ALL,
                userOwnerId,
                ParameterizedTypeReference.forType(List.class)
        );
    }

    @Override
    public List<CatDto> getCatsWithCatColor(CatColor color, String userOwnerId) {
        List<String> list = List.of(color.toString(), userOwnerId);
        return template.convertSendAndReceiveAsType(
                Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_COLOR,
                list,
                ParameterizedTypeReference.forType(List.class)
        );
    }

    @Override
    public void addCatToFriends(String id, String friendId, String userOwnerId) throws Exception {
        List<String> list = List.of(id, friendId, userOwnerId);
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_ADD_FRIEND,
                list,
                ParameterizedTypeReference.forType(String.class));
        if (!Objects.equals(response, "OK")) throw new Exception();
    }

    @Override
    public List<CatDto> getFriendsByCatId(String catId) {
        return template.convertSendAndReceiveAsType(
                Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_FRIENDS,
                catId,
                ParameterizedTypeReference.forType(List.class)
        );
    }

    @Override
    public void deleteCatFromFriends(String id, String friendId, String userOwnerId) throws Exception {
        List<String> list = List.of(id, friendId, userOwnerId);
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_DELETE_FRIEND,
                list,
                ParameterizedTypeReference.forType(String.class));
        if (!Objects.equals(response, "OK")) throw new Exception();
    }
}
