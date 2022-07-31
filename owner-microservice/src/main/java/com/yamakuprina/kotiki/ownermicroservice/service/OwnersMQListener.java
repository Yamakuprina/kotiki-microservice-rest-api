package com.yamakuprina.kotiki.ownermicroservice.service;

import com.yamakuprina.kotiki.ownermicroservice.config.MQConfig;
import entities.OwnerDto;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableRabbit
@Component
public class OwnersMQListener {

    private final OwnerService ownerService;

    public OwnersMQListener(@Autowired OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RabbitListener(queues = MQConfig.OWNERS_QUEUE_ID)
    public OwnerDto findById(String id) {
        OwnerDto ownerDto = ownerService.findById(id);
        return ownerDto == null? new OwnerDto() : ownerDto;
    }

    @RabbitListener(queues = MQConfig.OWNERS_QUEUE_SAVE)
    public String save(OwnerDto ownerDto) {
        try {
            ownerService.save(ownerDto);
            return "OK";
        } catch (Exception e) {
            return "500";
        }
    }

    @RabbitListener(queues = MQConfig.OWNERS_QUEUE_DELETE)
    public String delete(String id) {
        try {
            if (ownerService.findById(id)==null) return "404";
            ownerService.delete(id);
            return "OK";
        } catch (Exception e) {
            return "500";
        }
    }

    @RabbitListener(queues = MQConfig.OWNERS_QUEUE_ALL)
    public List<OwnerDto> getAllOwners(String all) {
        return ownerService.getAllOwners();
    }

}
