package org.example._backend.dao;

import org.example._backend.entity.Impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EditProfileDAO extends JpaRepository<UserEntity,String> {
    @Query(value = "SELECT COUNT(*) FROM user", nativeQuery = true)
    int getTotalUserCount();

    @Query(value = "SELECT COUNT(*) FROM user WHERE role = 'CUSTOMER'", nativeQuery = true)
    int getCustomerCount();

    @Query(value = "SELECT COUNT(*) FROM user WHERE role = 'ADMIN'", nativeQuery = true)
    int getAdminCount();
}
