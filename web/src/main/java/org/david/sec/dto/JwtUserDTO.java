package org.david.sec.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class JwtUserDTO extends GetUserDTO {
    private final String token;

    @Builder(builderMethodName = "jwtUserDTOBuilder")
    public JwtUserDTO(String email, String username, String avatar, Set<String> roles, String token) {
        super(email, username, avatar, roles);
        this.token = token;
    }

}
