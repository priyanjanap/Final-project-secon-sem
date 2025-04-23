package org.example._backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example._backend.dao.UserDAO;
import org.example._backend.dto.impl.UserWithKey;
import org.example._backend.entity.Impl.UserEntity;
import org.example._backend.service.EmailService;
import org.example._backend.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;
    private final EmailService emailService;

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userDAO.findByEmail(username).
                        orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

    }



    @Override
    public boolean sendCodeToChangePassword(UserWithKey userWithKey) {
        Optional<UserEntity> byEmail = userDAO.findByEmail(userWithKey.getEmail());
        if (byEmail.isPresent()) {
            emailService.sendEmail(userWithKey.getEmail(), "Your password change Code From  NEXOLEND", "Dont share with anyone:  " + userWithKey.getCode());
            return true;
        }
        return false;

    }


}
