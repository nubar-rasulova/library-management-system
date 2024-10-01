package com.example.library.mapper;

import com.example.library.dto.BookDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Book;

public class BookMapper {
    public static BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .authorId(book.getAuthor() != null ? book.getAuthor().getId() : null)
                .build();
    }

    public static Book toEntity(BookDTO bookDTO, Author author) {
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .author(author)
                .build();
    }
}
