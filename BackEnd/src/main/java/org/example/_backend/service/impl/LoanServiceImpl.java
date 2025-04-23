package org.example._backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example._backend.dao.LoanDAO;
import org.example._backend.dao.UserDAO;
import org.example._backend.dto.impl.LoanDTO;
import org.example._backend.entity.Impl.LoanEntity;
import org.example._backend.entity.Impl.UserEntity;
import org.example._backend.secureAndResponse.response.JwtAuthResponse;
import org.example._backend.secureAndResponse.secure.SingIn;
import org.example._backend.service.AuthenticationService;
import org.example._backend.service.JwtService;
import org.example._backend.service.LoanService;
import org.example._backend.util.GenerateID;
import org.example._backend.util.ModelMapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanDAO loanDAO;
    @Autowired
    private ModelMapper modelMapper;
    private final JwtService jwtService;

//    @Override
//    public LoanDTO saveLoan(LoanDTO loan) {
//        LoanDTO loanDTO = LoanDTO.builder()
//                .loanId(GenerateID.generateId(loanDAO, "LOAN"))
//                .loanAmount(loan.getLoanAmount())
//                .loanType(loan.getLoanType())
//                .userId(loan.getUserId())
//                .Premium(loan.getPremium())
//                .PremiumAmount(loan.getPremiumAmount())
//                .StartloanDate(loan.getStartloanDate())
//                .EndloanDate(loan.getEndloanDate())
//                .build();
//
//        LoanEntity loanEntity = loanDAO.save(mapping.toLoanEntity(loanDTO));
//
//        return mapping.toLoanEntity(loanEntity);
//    }
@Override
public LoanDTO saveLoan(LoanDTO loanDTO) {
    String generatedLoanId = GenerateID.generateId(loanDAO, "LOAN");
    loanDTO.setLoanId(generatedLoanId);

    LoanEntity loanEntity = modelMapper.map(loanDTO, LoanEntity.class);
    loanEntity = loanDAO.save(loanEntity);
    System.out.println(loanEntity);
    return modelMapper.map(loanEntity, LoanDTO.class);
}


    @Override
    public List<LoanDTO> getAllLoans() {
        return loanDAO.findAll().stream()
                .map(entity -> LoanDTO.builder()
                        .loanId(entity.getLoanId())
                        .userId(entity.getUserId())
                        .loanType(entity.getLoanType())
                        .loanAmount(entity.getLoanAmount())
                        .Premium(entity.getPremium())
                        .PremiumAmount(entity.getPremiumAmount())
                        .StartloanDate(entity.getStartloanDate())
                        .EndloanDate(entity.getEndloanDate())
                        .build())
                .collect(Collectors.toList());    }

    @Override
    public LoanDTO getLoanById(String id) {
        return null;
    }

    @Override
    public void deleteLoan(String id) {

    }

    @Override
    public List<String> getAllLoanIds() {
        return loanDAO.findAllLoanIds();
    }


}
