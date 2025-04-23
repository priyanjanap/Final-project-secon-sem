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
@Table(name = "loan")
public class LoanEntity implements SuperEntity {
    @Id
    private  String loanId;
    private  String userId;
    private  String loanType;
    private  String loanAmount;
    private  int Premium;
    private double PremiumAmount;
    private Date StartloanDate;
    private Date EndloanDate;

    private double totalPaidAmount = 0;
    private double remainingAmount;


}
