package org.example._backend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    public @NonNull String getToken() {
        return token;
    }

    public void setToken(@NonNull String token) {
        this.token = token;
    }

    @NonNull
    private String token;


}
