package org.example._backend.dao;

import org.example._backend.entity.Impl.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentDAO extends JpaRepository<PaymentEntity,String> {
    @Query(value = "SELECT * FROM payment ORDER BY CAST(SUBSTRING(payment_id, 5) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    PaymentEntity findLastRowNative();


}
