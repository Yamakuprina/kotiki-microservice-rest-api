package com.yamakuprina.kotiki.apigateway.service;

import com.yamakuprina.kotiki.apigateway.config.Queues;
import entities.CatColor;
import entities.CatDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

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
                Queues.ROUTING_KEY + Queues.USER_QUEUE_CATS_ALL,
                userOwnerId,
                ParameterizedTypeReference.forType(List.class)
        );
    }

    @Override
    public List<CatDto> getCatsWithCatColor(CatColor color, String userOwnerId) {
        List<String> list = List.of(color.toString(), userOwnerId);
        return template.convertSendAndReceiveAsType(
                Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.USER_QUEUE_CATS_COLOR,
                list,
                ParameterizedTypeReference.forType(List.class)
        );
    }

    @Override
    public void addCatToFriends(String id, String friendId, String userOwnerId) throws HttpStatusCodeException {
        if (id.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        if (friendId.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        List<String> list = List.of(id, friendId, userOwnerId);
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.USER_QUEUE_CATS_ADD_FRIEND,
                list,
                ParameterizedTypeReference.forType(String.class));
        if (Objects.equals(response, "404")) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        if (!Objects.equals(response, "OK")) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<CatDto> getFriendsByCatId(String catId) throws HttpStatusCodeException {
        if (catId.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        List<CatDto> catDtos = template.convertSendAndReceiveAsType(
                Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.USER_QUEUE_CATS_FRIENDS,
                catId,
                ParameterizedTypeReference.forType(List.class)
        );
        if (catDtos == null) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        return catDtos;
    }

    @Override
    public void deleteCatFromFriends(String id, String friendId, String userOwnerId) throws HttpStatusCodeException {
        if (id.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        if (friendId.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        List<String> list = List.of(id, friendId, userOwnerId);
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.USER_QUEUE_CATS_DELETE_FRIEND,
                list,
                ParameterizedTypeReference.forType(String.class));
        if (Objects.equals(response, "404")) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        if (!Objects.equals(response, "OK")) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
