package org.example._backend.secureAndResponse.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.Enum.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponse {
    private String token;
    private Role role;
    private String userId;

    public JwtAuthResponse(String token) {
        this.token = token;
    }
}
