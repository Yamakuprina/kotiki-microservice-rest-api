package com.yamakuprina.kotiki.apigateway.service;

import com.yamakuprina.kotiki.apigateway.userRepository.UserRepository;
import entities.User;
import entities.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStorageServiceImpl implements UserStorageService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final OwnerServiceRabbitSender ownerService;

    public UserStorageServiceImpl(@Autowired UserRepository userRepository, @Autowired OwnerServiceRabbitSender ownerService) {
        this.userRepository = userRepository;
        this.ownerService = ownerService;
    }

    @Override
    public void saveUser(UserDto userDto) throws HttpStatusCodeException {
        if (ownerService.findById(userDto.getOwnerId()) == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        User user = new User(userDto);
        String passwordEncoded = encoder.encode(userDto.getPassword());
        user.setPassword(passwordEncoded);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) throws HttpStatusCodeException {
        if (userId.length() != 36) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return usersToUserDtos(allUsers);
    }

    private List<UserDto> usersToUserDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
