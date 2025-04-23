package org.example._backend.service.impl;


import lombok.RequiredArgsConstructor;
import org.example._backend.Enum.Role;
import org.example._backend.dao.UserDAO;
import org.example._backend.dao.UserDetailsDAO;
import org.example._backend.dto.impl.ChangePasswordDTO;
import org.example._backend.dto.impl.UserDTO;
import org.example._backend.dto.impl.UserDetailsDTO;
import org.example._backend.entity.Impl.UserDetailsEntity;
import org.example._backend.entity.Impl.UserEntity;
import org.example._backend.exception.UserNotFoundException;
import org.example._backend.secureAndResponse.response.JwtAuthResponse;
import org.example._backend.secureAndResponse.secure.SearchSingUp;
import org.example._backend.secureAndResponse.secure.SingIn;
import org.example._backend.secureAndResponse.secure.SingUp;
import org.example._backend.service.AuthenticationService;
import org.example._backend.service.EmailService;
import org.example._backend.service.JwtService;
import org.example._backend.util.AuthUtil;
import org.example._backend.util.GenerateID;
import org.example._backend.util.ModelMapping;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ModelMapping mapping;
    private final UserDAO userDAO;
    private final JwtService jwtService;
    private final UserDetailsDAO userDetailsDAO;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;


    @Override
    public JwtAuthResponse signUp(SingUp signUp) {
        emailService.sendEmail(signUp.getEmail(), "Email From Nexolend", "Your user email:  " + signUp.getEmail() + "\n Your temporary password to login: " + signUp.getPassword());
        UserDTO userDTO = UserDTO.builder().user_id(GenerateID.generateId(userDAO, "USER")).email(signUp.getEmail()).password(passwordEncoder.encode(signUp.getPassword())).role(signUp.getRole()).build();

        UserEntity save = userDAO.save(mapping.toUserEntity(userDTO));
        String generateToken = jwtService.generateToken(save);
        return JwtAuthResponse.builder().token(generateToken).build();

    }
    @Override
    public String getUserIdByEmail(String email) {
        return userDAO.findUserIdByEmail(email);
    }
    @Override
    public JwtAuthResponse findUserByEmail(String email) {
        Optional<UserEntity> user = userDAO.findByEmail(email);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        String generateToken = jwtService.generateToken2(user);
        return new JwtAuthResponse(generateToken);
    }
    @Override
    public JwtAuthResponse generateTokenForEmail(String email) {
        Optional<UserEntity> userOptional = userDAO.findByEmail(email);
        System.out.println(userOptional);
        System.out.println("generateTokenForEmail");

        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not registered");
        }

        UserEntity user = userOptional.get();

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        System.out.println( claims);
        claims.put("role", user.getRole());

        String token = jwtService.generateTokenWithoutUserDetails(claims, user.getEmail());

        return new JwtAuthResponse(token);
    }

    @Override
    public JwtAuthResponse signIn(SingIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        UserEntity user = userDAO.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String userId = user.getUser_id();
        String generateToken = jwtService.generateToken(user);

        return JwtAuthResponse.builder()
                .token(generateToken)
                .role(Role.valueOf(user.getRole().name()))
                .userId(userId)
                .build();
    }


    @Override
    public JwtAuthResponse search(SearchSingUp searchSingUp) {
        UserDetailsDTO userDetailsEntity = UserDetailsDTO.builder()
                .accountType(searchSingUp.getAccountType())
                .accountNumber(searchSingUp.getAccountNumber())
                .ncc(searchSingUp.getNcc())
                .fullName(searchSingUp.getFullName())
                .email(searchSingUp.getEmail())
                .build();

        System.out.println("NCC in SearchSingUp Authentication class: " + searchSingUp.getNcc());
        System.out.println("NCC in DTO: " + userDetailsEntity.getNcc());

        Optional<UserDetailsEntity> get = userDetailsDAO.findByCriteria(mapping.toUserDetailsEntity(userDetailsEntity));
        System.out.println(userDetailsEntity);
        String generateToken = jwtService.generateToken(get);
        return new JwtAuthResponse(generateToken);
    }

    @Override
    public JwtAuthResponse refreshToken(String refreshToken) {
        String user = jwtService.extractUserName(refreshToken);
        UserEntity findUser = userDAO.findByEmail(user).orElseThrow(() -> new UserNotFoundException("Cant find User"));
        String token = jwtService.refreshToken(findUser);
        return JwtAuthResponse.builder().token(token).build();

    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        Optional<UserEntity> byEmail = userDAO.findByEmail(changePasswordDTO.getEmail());
        if (byEmail.isPresent()) {
            UserEntity referenceById = userDAO.getReferenceById(byEmail.get().getUser_id());
            referenceById.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            userDAO.save(referenceById);
        }
    }

    @Override
    public String getLoggedInUserId() {
        String email = AuthUtil.getLoggedInUserEmail();
        if (email != null) {
            System.out.println(email);
            return userDAO.findUserIdByEmail(email);

        }
        return null;
    }
}


