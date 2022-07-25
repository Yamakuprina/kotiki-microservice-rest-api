package com.yamakuprina.kotiki.apigateway.service;

import com.yamakuprina.kotiki.apigateway.config.Queues;
import entities.CatDto;
import entities.OwnerDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

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
    public OwnerDto findById(String id) {
        return template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_ID,
                id,
                ParameterizedTypeReference.forType(OwnerDto.class));
    }

    @Override
    public void save(OwnerDto ownerDto) throws Exception {
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_SAVE,
                ownerDto,
                ParameterizedTypeReference.forType(String.class));
        if (!Objects.equals(response, "OK")) throw new Exception();
    }

    @Override
    public void delete(String id) throws Exception {
        String response = template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_DELETE,
                id,
                ParameterizedTypeReference.forType(String.class));
        if (!Objects.equals(response, "OK")) throw new Exception("Couldnt delete");
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_ALL,
                "all",
                ParameterizedTypeReference.forType(List.class));
    }

    @Override
    public List<CatDto> getCatsByOwnerId(String ownerId) {
        return userService.getAllCats(ownerId);
    }

}
