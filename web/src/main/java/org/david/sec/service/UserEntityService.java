package org.david.sec.service;

import lombok.RequiredArgsConstructor;
import org.david.sec.model.UserEntity;
import org.david.sec.model.UserRole;
import org.david.sec.repository.UserEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, Long, UserEntityRepository> {
    private final PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findByUsername(final String username) {
        return this.repository.findByUsername(username);
    }

    public UserEntity createUser(final UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Set.of(UserRole.USER));

        return save(userEntity);
    }
}
