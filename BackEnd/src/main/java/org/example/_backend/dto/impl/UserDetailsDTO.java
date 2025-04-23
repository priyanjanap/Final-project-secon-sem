package org.example._backend.dto.impl;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.Enum.AccountType;
import org.example._backend.dto.SuperDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDTO implements SuperDTO {


    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String accountNumber;
    @NotNull(message = "ncc cannot be null")
    @NotNull
    private String ncc;
    private String fullName;

    @Email
    @Column(unique = true)
    private String email;


}