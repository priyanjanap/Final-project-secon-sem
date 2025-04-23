package org.example._backend.service;

import org.example._backend.dto.impl.ChangePasswordDTO;
import org.example._backend.entity.Impl.UserEntity;
import org.example._backend.secureAndResponse.response.JwtAuthResponse;
import org.example._backend.secureAndResponse.secure.SingIn;
import org.example._backend.secureAndResponse.secure.SingUp;
import org.example._backend.secureAndResponse.secure.SearchSingUp;

import java.util.Optional;


public interface AuthenticationService {
    JwtAuthResponse signUp(SingUp signUp);
    JwtAuthResponse signIn(SingIn signIn);
    JwtAuthResponse search(SearchSingUp SearchSingUp);

    JwtAuthResponse refreshToken(String refreshToken);
    void changePassword(ChangePasswordDTO changePasswordDTO);
    String getLoggedInUserId();

    String getUserIdByEmail(String email);

    JwtAuthResponse findUserByEmail(String email);
    JwtAuthResponse generateTokenForEmail(String email);


}
