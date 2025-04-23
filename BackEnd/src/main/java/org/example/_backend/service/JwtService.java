package org.example._backend.service;
import org.example._backend.entity.Impl.UserDetailsEntity;
import org.example._backend.entity.Impl.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Optional;

public interface JwtService {

    String extractUserName(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails);
    String generateToken1(UserDetails userDetails);
    String refreshToken(UserDetails userDetails);

    String generateToken(Optional<UserDetailsEntity> get);
    String generateToken2(Optional<UserEntity> userEntityOptional);
    String generateTokenWithoutUserDetails(Map<String, Object> extraClaims, String subject);

}
