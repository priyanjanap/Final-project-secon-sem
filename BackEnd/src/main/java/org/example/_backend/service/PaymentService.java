package org.example._backend.service;

import org.example._backend.dto.impl.PaymentDTO;
import org.example._backend.entity.Impl.PaymentEntity;

import java.util.List;

public interface PaymentService {
    PaymentDTO savePayment(PaymentDTO paymentDTO);
    List<PaymentDTO> getAllPayments();

    public PaymentDTO makePayment(String userId, String loanId, double amount, String paymentType);

}
