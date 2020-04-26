package org.david.sec.controller;

import lombok.RequiredArgsConstructor;
import org.david.sec.model.UserEntity;
import org.david.sec.service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService service;

    @PostMapping("")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }
}