package com.example.library.service.impl;

import com.example.library.dto.LoanDTO;
import com.example.library.entity.Book;
import com.example.library.entity.Loan;
import com.example.library.entity.Member;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.mapper.LoanMapper;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.MemberRepository;
import com.example.library.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanServiceImpl(LoanRepository loanRepository,
                           BookRepository bookRepository,
                           MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public LoanDTO createLoan(LoanDTO loanDTO) {
        Book book = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Member member = memberRepository.findById(loanDTO.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        Loan loan = LoanMapper.toEntity(loanDTO, book, member);
        loan = loanRepository.save(loan);
        return LoanMapper.toDTO(loan);
    }

    @Override
    public LoanDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        return LoanMapper.toDTO(loan);
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(LoanMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDTO updateLoan(Long id, LoanDTO loanDTO) {
        Loan existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        Book book = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Member member = memberRepository.findById(loanDTO.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        existingLoan.setLoanDate(loanDTO.getLoanDate());
        existingLoan.setReturnDate(loanDTO.getReturnDate());
        existingLoan.setBook(book);
        existingLoan.setMember(member);

        existingLoan = loanRepository.save(existingLoan);
        return LoanMapper.toDTO(existingLoan);
    }

    @Override
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}
