package com.example.library.mapper;

import com.example.library.dto.AuthorDTO;
import com.example.library.entity.Author;

public class AuthorMapper {
    public static AuthorDTO toDTO(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .biography(author.getBiography())
                .build();
    }

    public static Author toEntity(AuthorDTO authorDTO) {
        return Author.builder()
                .id(authorDTO.getId())
                .name(authorDTO.getName())
                .biography(authorDTO.getBiography())
                .build();
    }
}
