package com.yamakuprina.kotiki.apigateway.controller;

import com.yamakuprina.kotiki.apigateway.service.UserStorageService;
import entities.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserStorageService userStorageService;

    @PostMapping("/users/save")
    public ResponseEntity<Object> saveUser(@RequestBody UserDto userDto) {
        try {
            userStorageService.saveUser(userDto);
            return ResponseEntity.ok("User successfully saved.");
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @GetMapping("/users/all")
    public ResponseEntity<Object> allUsers() {
        try {
            return ResponseEntity.ok(userStorageService.getAllUsers());
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam String userId) {
        try {
            userStorageService.deleteUser(userId);
            return ResponseEntity.ok("User successfully deleted.");
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }
}
