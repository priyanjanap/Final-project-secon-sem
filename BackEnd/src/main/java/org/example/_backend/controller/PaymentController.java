package org.example._backend.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.example._backend.dto.impl.PaymentDTO;
import org.example._backend.service.PaymentService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

//    @PostMapping("/savePayment")
//    public PaymentDTO savePayment(@RequestBody PaymentDTO paymentDTO) {
//        return paymentService.save(paymentDTO);
//    }
@RolesAllowed({"CUSTOMER"})


@PostMapping("/savePayment")
public ResponseEntity<PaymentDTO> savePayment(@RequestBody PaymentDTO dto) {
    PaymentDTO saved = paymentService.makePayment(null,
            dto.getLoanId(), dto.getAmount(),dto.getPaymentType()
    );
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
}
    @GetMapping("/getAll")
    public List<PaymentDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }
//    @PostMapping("/make")
//    public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO paymentDTO) {
//        PaymentDTO savedPayment = paymentService.makePayment(
//                paymentDTO.getLoanId(),
//                paymentDTO.getLoanId(),
//                paymentDTO.getAmount(),
//                paymentDTO.getPaymentType()
//        );
//        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
//    }
}
