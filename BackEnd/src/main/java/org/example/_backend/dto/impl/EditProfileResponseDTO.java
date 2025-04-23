package org.example._backend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditProfileResponseDTO {
    private int totalUsers;
    private int adminCount;
    private int customerCount;
}
