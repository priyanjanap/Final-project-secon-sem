package org.example._backend.entity.Impl;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.Enum.AccountType;
import org.example._backend.entity.SuperEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsEntity implements SuperEntity, UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String accountNumber;

    @Id
    @Column(name = "ncc")

    private String ncc;



    private String fullName;

    @Email
    @Column(unique = true)
    private String email;



}