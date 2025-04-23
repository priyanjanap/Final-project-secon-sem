package org.example._backend.dao;

import org.example._backend.entity.Impl.PaymentDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsDAO extends JpaRepository<PaymentDetailsEntity,String> {
}
