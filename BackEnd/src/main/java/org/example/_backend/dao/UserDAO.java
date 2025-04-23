package org.example._backend.dao;


import org.example._backend.entity.Impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDAO  extends JpaRepository<UserEntity, String> {
    @Query(value = "SELECT * FROM user WHERE user_id = (SELECT user_id FROM user ORDER BY CAST(SUBSTRING(user_id, 6) AS UNSIGNED) DESC LIMIT 1);", nativeQuery = true)
    UserEntity findLastRowNative();

    Optional<UserEntity> findByEmail(@Param("email")String email);

    @Query(value = "SELECT u.user_id FROM UserEntity u WHERE u.email = :email",nativeQuery = true)
    String findUserIdByEmail(@Param("email") String email);




}
