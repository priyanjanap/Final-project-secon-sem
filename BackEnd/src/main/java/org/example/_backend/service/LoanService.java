package org.example._backend.service;

import org.example._backend.dto.impl.LoanDTO;
import org.example._backend.secureAndResponse.secure.SingIn;

import java.util.List;

public interface LoanService {
    LoanDTO saveLoan(LoanDTO loan);
    List<LoanDTO> getAllLoans();
    LoanDTO getLoanById(String id);
    void deleteLoan(String id);
    List<String> getAllLoanIds();

}
