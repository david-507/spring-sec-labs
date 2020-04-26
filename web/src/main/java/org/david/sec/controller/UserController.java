package org.david.sec.controller;

import lombok.RequiredArgsConstructor;
import org.david.sec.dto.CreateUserDTO;
import org.david.sec.dto.GetUserDTO;
import org.david.sec.dto.converter.UserDtoConverter;
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
    private final UserDtoConverter converter;

    @PostMapping("")
    public ResponseEntity<GetUserDTO> createUser(@RequestBody CreateUserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(converter.convert(service.createUser(user)));
    }
}