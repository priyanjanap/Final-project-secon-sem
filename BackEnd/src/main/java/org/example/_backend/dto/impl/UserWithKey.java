package org.example._backend.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithKey {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String code;
}
