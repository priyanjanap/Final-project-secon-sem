package org.example._backend.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example._backend.entity.Impl.UserDetailsEntity;
import org.example._backend.entity.Impl.UserEntity;
import org.example._backend.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class JwtServiceImpl implements JwtService {


    @Value("${spring.jwtKey}")

    private String jwtSigninKey;


    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    @Override
    public String generateToken1(UserDetails userDetails) {
        UserEntity userEntity = (UserEntity) userDetails;
        return generateToken1(userEntity);
    }


    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    @Override
    public String refreshToken(UserDetails userDetails) {
        return refreshToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Optional<UserDetailsEntity> userEntityOptional) {
        if (userEntityOptional.isPresent()) {
            UserDetailsEntity userDetails = userEntityOptional.get();
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("role", userDetails.getAccountType()); // Example claim

            return generateToken(extraClaims, (UserDetails) userDetails);
        }
        throw new IllegalArgumentException("User details Entity not found");
    }
    @Override
    public String generateTokenWithoutUserDetails(Map<String, Object> extraClaims, String subject) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSigninKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    @Override
    public String generateToken2(Optional<UserEntity> userEntityOptional) {
        if (userEntityOptional.isPresent()) {
            UserEntity userDetails = userEntityOptional.get();
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("email", userDetails.getEmail()); // Example claim

            return generateToken(extraClaims, (UserDetails) userDetails);
        }
        throw new IllegalArgumentException("User Entity not found");
    }



    public String generateToken1(UserEntity userEntity) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("user_id", userEntity.getUser_id());

        return generateToken(extraClaims, userEntity);
    }


    private String refreshToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        extractClaims.put("role", userDetails.getAuthorities());
        Date now = new Date();
        Date refreshExpire = new Date(now.getTime() + 1000 * 600 * 600);

        return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername()).setExpiration(refreshExpire).signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        System.out.println(userDetails);
        System.out.println("HELLO WORLDS !!!!!");
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 3000000)).signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigninKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
