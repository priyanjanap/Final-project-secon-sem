package org.example._backend.dao;

import org.example._backend.Enum.AccountType;
import org.example._backend.entity.Impl.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsDAO extends JpaRepository<UserDetailsEntity, String> {

    @Query("SELECT u FROM UserDetailsEntity u WHERE " +
            "(:accountType IS NULL OR u.accountType = :accountType) AND " +
            "(:accountNumber IS NULL OR u.accountNumber = :accountNumber) AND " +
            "(COALESCE(:ncc, '') = '' OR u.ncc = :ncc) AND " +
            "(:fullName IS NULL OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))) AND " +
            "(:email IS NULL OR u.email = :email)")

    Optional<UserDetailsEntity> findByCriteria(
            @Param("accountType") AccountType accountType,
            @Param("accountNumber") String accountNumber,
            @Param("ncc") String ncc,
            @Param("fullName") String fullName,
            @Param("email") String email
    );

    default Optional<UserDetailsEntity> findByCriteria(UserDetailsEntity userDetailsEntity) {
        System.out.println("UserDetailsDAO"+userDetailsEntity.getNcc());
        System.out.println("UserDetailsDAO"+userDetailsEntity.getAccountNumber());
        System.out.println("UserDetailsDAO"+userDetailsEntity.getAccountType());
        System.out.println("UserDetailsDAO"+userDetailsEntity.getFullName());
        System.out.println("UserDetailsDAO"+userDetailsEntity.getEmail());
        return findByCriteria(
                userDetailsEntity.getAccountType(),
                userDetailsEntity.getAccountNumber(),
                userDetailsEntity.getNcc(),
                userDetailsEntity.getFullName(),
                userDetailsEntity.getEmail()
        );
    }
}
