package com.yamakuprina.kotiki.apigateway.service;


import entities.UserDto;

import java.util.List;

public interface UserStorageService {
    void saveUser(UserDto userDto);

    void deleteUser(String userId);

    List<UserDto> getAllUsers();

//    void setOwnerToUser(String userId, String ownerId) throws Exception;
}
