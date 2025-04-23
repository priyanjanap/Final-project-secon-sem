package org.example._backend.service;

import org.example._backend.dto.impl.UserWithKey;
import org.example._backend.entity.Impl.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();

    boolean sendCodeToChangePassword(UserWithKey userWithKey);

}
