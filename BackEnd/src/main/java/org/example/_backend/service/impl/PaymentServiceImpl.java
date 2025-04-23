package org.example._backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example._backend.dao.LoanDAO;
import org.example._backend.dao.PaymentDAO;
import org.example._backend.dao.PaymentDetailsDAO;
import org.example._backend.dto.impl.PaymentDTO;
import org.example._backend.entity.Impl.LoanEntity;
import org.example._backend.entity.Impl.PaymentDetailsEntity;
import org.example._backend.entity.Impl.PaymentEntity;
import org.example._backend.exception.DataPersistException;
import org.example._backend.service.PaymentService;
import org.example._backend.util.GenerateID;
import org.example._backend.util.ModelMapping;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;
    private final ModelMapping modelMapping;
    private final ModelMapper modelMapper;
    private final LoanDAO loanDAO;
    private final PaymentDetailsDAO paymentDetailsDAO;

    @Override
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        String generatedId = GenerateID.generateId(paymentDAO, "PAY");
        paymentDTO.setPaymentId(generatedId);

        PaymentEntity entity = modelMapper.map(paymentDTO, PaymentEntity.class);
        entity = paymentDAO.save(entity);
        System.out.println(entity+"service");
        return modelMapper.map(entity, PaymentDTO.class);
    }


    @Override
    public List<PaymentDTO> getAllPayments() {
        List<PaymentEntity> paymentEntities = paymentDAO.findAll();
        return paymentEntities.stream()
                .map(paymentEntity -> new PaymentDTO(
                        paymentEntity.getPaymentId(),
                        paymentEntity.getLoanId(),
                        paymentEntity.getAmount(),
                        paymentEntity.getDate(),
                        paymentEntity.getPaymentType()
                ))
                .collect(Collectors.toList());

    }

    @Override @Transactional
    public PaymentDTO makePayment(String userId, String loanId, double amount, String paymentType) {
        LoanEntity loan = loanDAO.findByLoanId( loanId);
        if (loan == null) throw new RuntimeException("Loan not found");

        String paymentId = GenerateID.generateId(paymentDAO, "PAY");

        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentId(paymentId);
        payment.setLoanId(loanId);
        payment.setAmount(amount);
        payment.setDate(new Date());
        payment.setPaymentType(paymentType);
        paymentDAO.save(payment);

        PaymentDetailsEntity details = new PaymentDetailsEntity();
        details.setPayID(paymentId);
        details.setLoanID(loanId);
        details.setUserID(userId);
        details.setPaidAmount(amount);
        details.setTotalAmount(Double.parseDouble(loan.getLoanAmount()));
        details.setDate(new Date());
        details.setPaymentType(paymentType);
        paymentDetailsDAO.save(details);

        double totalPaid = loan.getTotalPaidAmount() + amount;
        loan.setTotalPaidAmount(totalPaid);
        loan.setRemainingAmount(Double.parseDouble(loan.getLoanAmount()) - totalPaid);
        loanDAO.save(loan);

        return modelMapper.map(payment, PaymentDTO.class);
    }


//    @Override
//    public PaymentEntity save(PaymentDTO paymentDTO) {
//        PaymentDTO paymentDTO1 = PaymentDTO.builder().paymentId(GenerateID.generateId(paymentDAO, "PAY")).PaymentType(paymentDTO.getPaymentType()).Amount(paymentDTO.getAmount()).date(paymentDTO.getDate()).LoanId(paymentDTO.getLoanId()).build();
//
//
//        PaymentEntity paymentEntity = paymentDAO.save(modelMapping.toPaymentEntity(paymentDTO1));
//
//        return paymentEntity;
//
//    }
}
