package org.example._backend.secureAndResponse.secure;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.Enum.Role;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingUp {
    @Null(message = "Id generate by backend")
    private String user_id;
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Role role;
}
