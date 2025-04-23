package org.example._backend.controller;




import lombok.RequiredArgsConstructor;
import org.example._backend.dto.impl.ChangePasswordDTO;
import org.example._backend.dto.impl.Token;
import org.example._backend.dto.impl.UserWithKey;
import org.example._backend.entity.Impl.UserDetailsEntity;
import org.example._backend.entity.Impl.UserEntity;
import org.example._backend.secureAndResponse.response.JwtAuthResponse;
import org.example._backend.secureAndResponse.secure.SearchSingUp;
import org.example._backend.secureAndResponse.secure.SingIn;
import org.example._backend.secureAndResponse.secure.SingUp;
import org.example._backend.service.AuthenticationService;
import org.example._backend.service.UserService;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final AuthenticationService authenticationService;

    private final UserService userService;



    @PostMapping("/signUp")
    public ResponseEntity<JwtAuthResponse> signup(@RequestBody SingUp signup) {
        return ResponseEntity.ok(authenticationService.signUp(signup));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SingIn signIn) {
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }

    @PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthResponse> refreshToken(@RequestBody() Token token) {
        System.out.println(token.getToken());
        return ResponseEntity.ok(authenticationService.refreshToken(token.getToken()));
    }
    @PostMapping("/searchUsers")
    public ResponseEntity<JwtAuthResponse> searchUsers(@RequestBody SearchSingUp searchSingUp) {
        System.out.println(searchSingUp);
        return ResponseEntity.ok(authenticationService.search(searchSingUp));
    }
    @GetMapping("/userId")
    public ResponseEntity<String> getLoggedInUserId() {
        String userId = authenticationService.getLoggedInUserId();
        System.out.println(userId);
        return ResponseEntity.ok(userId);
    }
    @PostMapping("/getUserID")
    public String getUserIdFromSignIn(@RequestBody SingIn signIn) {
        JwtAuthResponse response = authenticationService.signIn(signIn);

        String userId = response.getUserId();

        System.out.println("User ID: " + userId);

        return userId;
    }
    @PostMapping("/searchUserEmail")
    public ResponseEntity<JwtAuthResponse> searchByEmail(@RequestParam String email) {
        System.out.println(email+"UserController");
//      return  ResponseEntity.ok(authenticationService.findUserByEmail(email));
        return ResponseEntity.ok(authenticationService.generateTokenForEmail(email));
    }
    @PostMapping(value = "/sendCode")
    public ResponseEntity<Void> sendCode(@RequestBody() UserWithKey userWithKey) {
        if (userService.sendCodeToChangePassword(userWithKey)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody() ChangePasswordDTO changePasswordDTO) {
        try {
            authenticationService.changePassword(changePasswordDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
