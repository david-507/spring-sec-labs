package org.david.sec.service;

import lombok.RequiredArgsConstructor;
import org.david.sec.dto.CreateUserDTO;
import org.david.sec.errors.exceptions.NoMatchingPasswordsException;
import org.david.sec.model.UserEntity;
import org.david.sec.model.UserRole;
import org.david.sec.repository.UserEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

import java.util.Optional;
import java.util.Set;



@Service
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, Long, UserEntityRepository> {
    private final PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findByUsername(final String username) {
        return this.repository.findByUsername(username);
    }

    public UserEntity createUser(final CreateUserDTO dto) {
        if(!matchingPasswords(dto))
            throw new NoMatchingPasswordsException();

        UserEntity user = UserEntity.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .avatar(dto.getAvatar())
                .roles(Set.of(UserRole.USER)).build();

        return save(user);
    }

    private boolean matchingPasswords(CreateUserDTO dto) {
        if(isNull(dto) || isNull(dto.getPassword()) || isNull(dto.getPasswordRepeat()))
            return false;
        return dto.getPassword().contentEquals(dto.getPasswordRepeat());
    }
}
