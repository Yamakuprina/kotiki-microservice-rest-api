package com.yamakuprina.kotiki.apigateway.service;

import com.yamakuprina.kotiki.apigateway.config.Queues;
import com.yamakuprina.kotiki.apigateway.userDetails.KotikiUserDetails;
import entities.CatColor;
import entities.CatDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Objects;

@Service
public class CatServiceRabbitSender implements CatService {

    private final AmqpTemplate template;
    private final OwnerService ownerService;

    public CatServiceRabbitSender(@Autowired AmqpTemplate template, @Autowired OwnerService ownerService) {
        this.template = template;
        this.ownerService = ownerService;
    }

    @Override
    public List<CatDto> getAllCats() {
        return template.convertSendAndReceiveAsType(
                Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_ALL,
                "all",
                ParameterizedTypeReference.forType(List.class)
        );
    }

    @Override
    public CatDto findById(String id) throws HttpStatusCodeException {
        if (id.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        CatDto catDto = template.convertSendAndReceiveAsType(
                Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_ID,
                id,
                ParameterizedTypeReference.forType(CatDto.class)
        );
        if (catDto==null) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        if (Objects.equals(catDto.getId(), null)) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        return catDto;
    }

    @Override
    public void save(CatDto cat) throws HttpStatusCodeException {
        if (cat.getOwnerId() == null)
            cat.setOwnerId(((KotikiUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOwnerId());
        else if (ownerService.findById(cat.getOwnerId()) == null) throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"No Owner found");
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_SAVE,
                cat,
                ParameterizedTypeReference.forType(String.class));
        if (!Objects.equals(response, "OK")) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void delete(String id) throws HttpStatusCodeException {
        if (id.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_DELETE,
                id,
                ParameterizedTypeReference.forType(String.class));
        if (Objects.equals(response, "404")) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        if (!Objects.equals(response, "OK")) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<CatDto> getCatsWithCatColor(CatColor color) {
        return template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_COLOR,
                color,
                ParameterizedTypeReference.forType(List.class));
    }

    @Override
    public List<CatDto> getFriendsById(String id) throws HttpStatusCodeException {
        if (id.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        List<CatDto> catDtos = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_FRIENDS,
                id,
                ParameterizedTypeReference.forType(List.class));
        if (Objects.equals(catDtos,null)) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        return catDtos;
    }

    @Override
    public void addCatToFriends(String id, String friendId) throws HttpStatusCodeException {
        if (id.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        if (friendId.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        List<String> list = List.of(id, friendId);
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_ADD_FRIEND,
                list,
                ParameterizedTypeReference.forType(String.class));
        if (Objects.equals(response, "404")) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        if (!Objects.equals(response, "OK")) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteCatFromFriends(String id, String friendId) throws HttpStatusCodeException {
        if (id.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        if (friendId.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        List<String> list = List.of(id, friendId);
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.CATS_QUEUE_DELETE_FRIEND,
                list,
                ParameterizedTypeReference.forType(String.class));
        if (Objects.equals(response, "404")) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        if (!Objects.equals(response, "OK")) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
