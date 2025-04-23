package org.example._backend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._backend.dto.SuperDTO;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO implements SuperDTO {
    private  String loanId;
    private  String userId;
    private  String loanType;
    private  String loanAmount;
    private  int Premium;
    private double PremiumAmount;
    private Date StartloanDate;
    private Date EndloanDate;

}
