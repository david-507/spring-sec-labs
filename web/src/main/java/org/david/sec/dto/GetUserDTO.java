package org.david.sec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDTO {
    private String email;
    private String username;
    private String avatar;
    private Set<String> roles;
}