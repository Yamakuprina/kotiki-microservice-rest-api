package com.yamakuprina.kotiki.apigateway.service;

import entities.UserDto;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

public interface UserStorageService {
    void saveUser(UserDto userDto) throws HttpStatusCodeException;

    void deleteUser(String userId) throws HttpStatusCodeException;

    List<UserDto> getAllUsers();
}
