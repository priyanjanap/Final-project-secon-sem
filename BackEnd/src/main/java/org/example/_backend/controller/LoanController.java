package org.example._backend.controller;

import lombok.RequiredArgsConstructor;
import org.example._backend.dto.impl.LoanDTO;
import org.example._backend.secureAndResponse.response.JwtAuthResponse;
import org.example._backend.secureAndResponse.secure.SingIn;
import org.example._backend.service.AuthenticationService;
import org.example._backend.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/loans")
@RequiredArgsConstructor
@CrossOrigin
public class LoanController {
    private final LoanService loanService;
    @PostMapping("/saveLoan")
    public ResponseEntity<LoanDTO> saveLoan(@RequestBody LoanDTO loanDTO) {
        LoanDTO savedLoan = loanService.saveLoan(loanDTO);
        System.out.println(savedLoan+"Loan Controller");
        return ResponseEntity.ok(savedLoan);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        System.out.println(loanService.getAllLoans());
        return ResponseEntity.ok(loanService.getAllLoans());
    }
    @GetMapping("/loan-ids")
    public ResponseEntity<List<String>> getAllLoanIds() {
        return ResponseEntity.ok(loanService.getAllLoanIds());
    }




}
