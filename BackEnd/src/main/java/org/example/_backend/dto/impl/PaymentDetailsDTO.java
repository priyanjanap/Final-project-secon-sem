package org.example._backend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.dto.SuperDTO;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetailsDTO implements SuperDTO {
    private String PayID;
    private String LoanID;
    private String UserID;
    private double PaidAmount;
    private double TotalAmount;
    private Date date;
    private String PaymentType;
}
