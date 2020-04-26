package org.david.sec.dto.converter;

import org.david.sec.dto.GetUserDTO;
import org.david.sec.model.UserEntity;
import org.david.sec.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public GetUserDTO convert(UserEntity entity) {
        return GetUserDTO.builder()
                .avatar(entity.getAvatar())
                .username(entity.getUsername())
                .roles(entity.getRoles().stream().map(UserRole::name).collect(Collectors.toSet()))
                .build();
    }
}