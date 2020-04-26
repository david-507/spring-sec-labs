package org.david.sec.dto.converter;

import org.david.sec.dto.GetUserDTO;
import org.david.sec.dto.JwtUserDTO;
import org.david.sec.model.UserEntity;
import org.david.sec.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.Set;
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

    public JwtUserDTO convert(UserEntity entity, String token) {
        return JwtUserDTO.jwtUserDTOBuilder()
                .email(entity.getEmail())
                .username(entity.getUsername())
                .avatar(entity.getAvatar())
                .roles(entity.getRoles().stream().map(UserRole::name).collect(Collectors.toSet()))
                .token(token)
                .build();
    }

    public JwtUserDTO jwtUserDTO(UserEntity entity) {
        return JwtUserDTO.jwtUserDTOBuilder()
                .avatar(entity.getAvatar())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .roles(entity.getRoles().stream().map(UserRole::name).collect(Collectors.toSet()))
                .build();
    }
}