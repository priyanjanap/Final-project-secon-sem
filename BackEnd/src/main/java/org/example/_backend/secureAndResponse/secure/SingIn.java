package org.example._backend.secureAndResponse.secure;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.Enum.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SingIn {

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

    @NotNull(message = "password cannot be null")
    private String password;
    @NotNull(message = "role cannot be null")
    private Role role;
}
