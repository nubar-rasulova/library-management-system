package com.example.library.service;

import com.example.library.dto.LoanDTO;

import java.util.List;

public interface LoanService {
    LoanDTO createLoan(LoanDTO loanDTO);
    LoanDTO getLoanById(Long id);
    List<LoanDTO> getAllLoans();
    LoanDTO updateLoan(Long id, LoanDTO loanDTO);
    void deleteLoan(Long id);
}
