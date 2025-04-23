package org.example._backend.dao;

import org.example._backend.entity.Impl.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanDAO extends JpaRepository<LoanEntity,String> {

    @Query(value = "SELECT * FROM loan WHERE loan_id = (SELECT loan_id FROM loan ORDER BY CAST(SUBSTRING(loan_id, 6) AS UNSIGNED) DESC LIMIT 1);", nativeQuery = true)
    LoanEntity findLastRowNative();

    @Query(value = "SELECT loan_id FROM loan WHERE user_id = ?1", nativeQuery = true)
    List<String> findLoanIdsByUserId(String customerId);

    @Query("SELECT l.loanId FROM LoanEntity l")
    List<String> findAllLoanIds();

    LoanEntity findByLoanId(String loanId);




}
