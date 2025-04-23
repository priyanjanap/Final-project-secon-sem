package org.example._backend.secureAndResponse.secure;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.Enum.AccountType;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchSingUp {
    private AccountType accountType;
    private String AccountNumber;
    @NotNull(message = "ncc cannot be null")
    private String ncc;
    private String FullName;

    @NotNull(message = "Email cannot be null")
    @Email
    private String Email;


}
