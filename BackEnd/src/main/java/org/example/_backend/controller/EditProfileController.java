package org.example._backend.controller;

import lombok.RequiredArgsConstructor;
import org.example._backend.dao.EditProfileDAO;
import org.example._backend.dao.UserDAO;
import org.example._backend.dto.impl.EditProfileResponseDTO;
import org.example._backend.dto.impl.UserDTO;
import org.example._backend.service.EditProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/Edit-profile")
@RequiredArgsConstructor
@CrossOrigin
public class EditProfileController {
    @Autowired
    private EditProfileService editProfileService;

    @Autowired
    private UserDAO userDAO;


    @GetMapping("/count")
    public ResponseEntity<EditProfileResponseDTO> getUserCounts() {
        EditProfileResponseDTO counts = editProfileService.getCount();
        return ResponseEntity.ok(counts);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userDAO.findAll()
                .stream()
                .map(userEntity -> new UserDTO(
                        userEntity.getUser_id(),
                        userEntity.getEmail(),
                        userEntity.getRole()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

}
