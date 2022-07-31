package com.yamakuprina.kotiki.apigateway.service;

import com.yamakuprina.kotiki.apigateway.config.Queues;
import entities.CatDto;
import entities.OwnerDto;
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
public class OwnerServiceRabbitSender implements OwnerService {

    private final UserService userService;
    private final AmqpTemplate template;

    public OwnerServiceRabbitSender(@Autowired UserService userService, @Autowired AmqpTemplate template) {
        this.userService = userService;
        this.template = template;
    }

    @Override
    public OwnerDto findById(String id) throws HttpStatusCodeException {
        if (id.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        OwnerDto ownerDto = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_ID,
                id,
                ParameterizedTypeReference.forType(OwnerDto.class));
        if (ownerDto == null) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        if (Objects.equals(ownerDto.getId(), null)) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        return ownerDto;
    }

    @Override
    public void save(OwnerDto ownerDto) throws HttpStatusCodeException {
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_SAVE,
                ownerDto,
                ParameterizedTypeReference.forType(String.class));
        if (!Objects.equals(response, "OK")) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void delete(String id) throws HttpStatusCodeException {
        if (id.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_DELETE,
                id,
                ParameterizedTypeReference.forType(String.class));
        if (Objects.equals(response, "404")) throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        if (!Objects.equals(response, "OK")) throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_ALL,
                "all",
                ParameterizedTypeReference.forType(List.class));
    }

    @Override
    public List<CatDto> getCatsByOwnerId(String ownerId) throws HttpStatusCodeException {
        if (ownerId.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        return userService.getAllCats(ownerId);
    }

}
