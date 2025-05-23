package org.example._backend.dto.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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
public class PaymentDTO implements SuperDTO {

    @Null(message = "Code is generated by the server")
    private String paymentId;

    @NotNull(message = "Loan ID is required")
    @JsonProperty("loanId")

    private String LoanId;

    @NotNull(message = "Amount is required")
    private double amount;

    @NotNull(message = "Date is required")
    private Date date;

    @NotNull(message = "Payment type is required")
    @JsonProperty("paymentType")

    private String PaymentType;
}
