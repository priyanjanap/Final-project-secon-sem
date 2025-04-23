package org.example._backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example._backend.dao.EditProfileDAO;
import org.example._backend.dto.impl.EditProfileResponseDTO;
import org.example._backend.service.EditProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EditProfileServiceImpl implements EditProfileService {
    @Autowired
    private EditProfileDAO editProfileDAO;
    @Override
    public EditProfileResponseDTO getCount() {
        int totalUserCount=editProfileDAO.getTotalUserCount();
        int totalAdminsCount=editProfileDAO.getAdminCount();
        int totalCustomerCount=editProfileDAO.getCustomerCount();
        return new EditProfileResponseDTO(totalUserCount,totalAdminsCount,totalCustomerCount);
    }
}
