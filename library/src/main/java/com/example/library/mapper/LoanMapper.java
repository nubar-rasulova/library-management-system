package com.example.library.mapper;

import com.example.library.dto.LoanDTO;
import com.example.library.entity.Book;
import com.example.library.entity.Loan;
import com.example.library.entity.Member;

public class LoanMapper {
    public static LoanDTO toDTO(Loan loan) {
        return LoanDTO.builder()
                .id(loan.getId())
                .loanDate(loan.getLoanDate())
                .returnDate(loan.getReturnDate())
                .bookId(loan.getBook() != null ? loan.getBook().getId() : null)
                .memberId(loan.getMember() != null ? loan.getMember().getId() : null)
                .build();
    }

    public static Loan toEntity(LoanDTO loanDTO, Book book, Member member) {
        return Loan.builder()
                .id(loanDTO.getId())
                .loanDate(loanDTO.getLoanDate())
                .returnDate(loanDTO.getReturnDate())
                .book(book)
                .member(member)
                .build();
    }
}
