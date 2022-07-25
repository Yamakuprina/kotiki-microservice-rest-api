package com.yamakuprina.kotiki.apigateway.service;


import com.yamakuprina.kotiki.apigateway.config.Queues;
import entities.CatDto;
import entities.OwnerDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (
                template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                        Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_SAVE,
                        ownerDto,
                        ParameterizedTypeReference.forType(String.class)) != "OK") throw new Exception();
    }

    @Override
    public void delete(String id) throws Exception {
        if (
                template.convertSendAndReceiveAsType(Queues.EXCHANGE,
                        Queues.ROUTING_KEY + Queues.OWNERS_QUEUE_DELETE,
                        id,
                        ParameterizedTypeReference.forType(String.class)) != "OK") throw new Exception();
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

//
//    @Autowired
//    private OwnerRepository ownerRepository;
//
//    @Autowired
//    private CatRepository catRepository;
//
//    @Override
//    public OwnerDto findById(String id) {
//        return new OwnerDto(ownerRepository.findById(id).orElseThrow().getName(), ownerRepository.findById(id).orElseThrow().getBirthDate());
//    }
//
//    @Override
//    public void save(OwnerDto ownerDto) {
//        Owner owner = new Owner(ownerDto.getName(), ownerDto.getBirthDate());
//        if (ownerDto.getCats() != null) {
//            owner.setCats(ownerDto.getCats());
//        }
//        if (ownerDto.getId() != null) {
//            owner.setId(ownerDto.getId());
//        }
//        ownerRepository.save(owner);
//    }
//
//    @Override
//    public void delete(String id) {
//        ownerRepository.deleteById(id);
//    }
//
//    @Override
//    public List<OwnerDto> getAllOwners() {
//        return ownersToOwnerDtos(ownerRepository.findAll());
//    }
//
//    @Override
//    public List<CatDto> getCatsByOwnerId(String ownerId) {
//        List<Cat> allCats = catRepository.findAll();
//        List<Cat> ownerCats = new ArrayList<>();
//        for (Cat cat : allCats) {
//            if (cat.getOwner().getId().equals(ownerId)) {
//                ownerCats.add(cat);
//            }
//        }
//        return catsToCatDtos(ownerCats);
//    }
//
//    private List<CatDto> catsToCatDtos(List<Cat> cats) {
//        ArrayList<CatDto> catDtos = new ArrayList<CatDto>();
//        for (Cat cat : cats) {
//            CatDto catDto = new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
//            catDtos.add(catDto);
//        }
//        return catDtos;
//    }
//
//    private List<OwnerDto> ownersToOwnerDtos(List<Owner> owners) {
//        ArrayList<OwnerDto> ownerDtos = new ArrayList<OwnerDto>();
//        for (Owner owner : owners) {
//            OwnerDto ownerDto = new OwnerDto(owner.getName(), owner.getBirthDate());
//            ownerDtos.add(ownerDto);
//        }
//        return ownerDtos;
//    }
}
