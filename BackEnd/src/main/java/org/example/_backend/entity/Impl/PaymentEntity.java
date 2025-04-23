package org.example._backend.entity.Impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.entity.SuperEntity;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity implements SuperEntity {
    @Id
    private  String paymentId;
    private  String LoanId;
    private  double Amount;
    private Date date;
    private String PaymentType;







}
