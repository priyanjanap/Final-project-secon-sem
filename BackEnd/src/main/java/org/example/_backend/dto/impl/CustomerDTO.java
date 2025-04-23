package org.example._backend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.Enum.AccountType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private String userId;
    private String email;
    private String fullName;
    private AccountType accountType;
    private String accountNumber;
    private String ncc;
}
